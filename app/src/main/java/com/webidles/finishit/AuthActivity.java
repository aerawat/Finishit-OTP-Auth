package com.webidles.finishit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private Button buttonContinue;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private EditText editCodeText,nameText;
    private int btnType = 0;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        pd= new ProgressDialog(AuthActivity.this);
        mAuth = FirebaseAuth.getInstance();
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonContinue = findViewById(R.id.buttonContinue);
        editCodeText = findViewById(R.id.editCodeText);
        nameText = findViewById(R.id.nameText);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnType == 0 ) {
                    //mProgressBar.setVisibility(View.VISIBLE);
                    pd.setMessage("Verifying your number..");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();
                    editTextPhone.setEnabled(false);
                    buttonContinue.setEnabled(false);
                    String phoneNumber = editTextPhone.getText().toString().trim();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,
                            60,
                            TimeUnit.SECONDS,
                            AuthActivity.this,
                            mCallBack
                    );
                }
                else
                {
                    buttonContinue.setEnabled(false);
                    String verificationCode = editCodeText.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(AuthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("On Verification Call",e.getMessage());
            }
            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;
                btnType =1;
                buttonContinue.setText("Verify Code");
                buttonContinue.setEnabled(true);
            }
        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FirebaseUser user = task.getResult().getUser();
                            saveData();
                            Intent mainIntent = new Intent(AuthActivity.this,MainActivity.class);
                            startActivity(mainIntent);
                            finish();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(AuthActivity.this, "There was an error in Login in", Toast.LENGTH_SHORT).show();
                            pd.cancel();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(AuthActivity.this, "Invalid code!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        }

    private void saveData()
    {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String name = nameText.getText().toString();
        String phoneNumber = editTextPhone.getText().toString();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        HashMap<String,String> userMap = new HashMap<>();
        userMap.put("Name",name);
        userMap.put("Phone",phoneNumber);
        userRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AuthActivity.this,"Welcome ",Toast.LENGTH_LONG).show();
            }
        });
    }
}
