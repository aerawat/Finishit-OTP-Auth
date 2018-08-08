package com.webidles.finishit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FaqActivity extends AppCompatActivity
{
    private ExpandableListView listView;
    private ExpandableListAdapter adapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    //private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        listView = findViewById(R.id.expandableListView);
        initData();
        adapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(adapter);
    }
    private String[] answers = {
            "Hey, we are \"Finish-it\", your new friend and what we do is help people to monitize their free time by helping busy people.We believe, you are a Superhero, and we want you to live longer and we are here to make it happen.How? If we save you 30 hours by running your errands, then you live almost 1.25 days more.",
            "Because we know being a superhero is hard. No one wants to pick up laundry clothes after such a tiring day at Job.So, we are here just a click away, ready to save you from hassle.",
            "You can literally use Finish-it for everything, ranging from ironing your jeans to folding your shirts,cleaning your apartment to moving your stuff.What not, you can even hire a JoJo to play a round of FIFA with you. \n" + "Shopping: Finish-it is your very own personal assistant. We get you what you need, no matter what it is. Be it medicine, daily need item, specialty products, street food or even pillows.\n" + "Pick & Drop: Pick, drop, deliver or move anything that is legal & transportable.\n" + "Tasks: We believe in making your life simpler. Deposit cheque, queue up to book tickets, get your PAN/Passport, Duplicate key made. So much more without having to step out to do any of these yourself.\n" + "Moving:Moving a bag to third floor is tough.We are right there at the door to take away your stress, sweat and hassle.\n" + "Personal Assistance: Writing emails to clients and scheduling appointments is even harder.Hire a personal assistant to handle your complex tasks now.\n",
            "You can place your request via App (by using your voice or via WhatsApp). Once we have accepted your request, we mobilize our skilled JoJos in getting things done for you. Our resources are well acquainted with the city & are armed with a thorough knowledge of your preferences. The execution is quick & as requested.",
            "JoJo are time traders, who go through vigrous training to give you a wonderful experience.",
            "Being a JoJo is hard, since you are going to serve our superheroes.But when you think you are ready to take such a huge responibilty, WhatsApp JoJo at 8851395512. You need to have an Aadhar Card, Pan Card, driving liscense and gets what, a lot of courage and basic etiquetts",
            "We fulfill service requests across Noida for anything that is legal, available & transportable. We are also constantly expanding our service & product range, as well as our service area, so feel free to test us anytime.",
            "We are operational when our JoJos are operational.So, we work almost 24*7.",
            "Your security is our topmost priority and your trust is our topmost desire.We don't want you to judge us without giving a try. We make sure your each experience is a wonderful one.And guess what, each task is being insured by upto Rs 10,00,000.\n",
            "When you place the request, our executive will let you know about the prices and the desired time needed to complete the request "
    };

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        listDataHeader.add("Who are you and what do you do?");
        listDataHeader.add("Why should we use Finish-it?");
        listDataHeader.add("What can I use Finish-it for?");
        listDataHeader.add("How to place an order?");
        listDataHeader.add("Who is a JoJo?");
        listDataHeader.add("How can I become a JoJo?");
        listDataHeader.add("Which cities are you operational in?");
        listDataHeader.add("What are you operation timing?");
        listDataHeader.add("How can I trust you with my goods?");
        listDataHeader.add("How would I know about the prices and the time needed to complete the request?");

        List<String> answer0 = new ArrayList<>();
        answer0.add(answers[0]);

        List<String> answer1 = new ArrayList<>();
        answer1.add(answers[1]);
        List<String> answer2 = new ArrayList<>();
        answer2.add(answers[2]);
        List<String> answer3 = new ArrayList<>();
        answer3.add(answers[3]);
        List<String> answer4 = new ArrayList<>();
        answer4.add(answers[4]);
        List<String> answer5 = new ArrayList<>();
        answer5.add(answers[5]);
        List<String> answer6 = new ArrayList<>();
        answer6.add(answers[6]);
        List<String> answer7 = new ArrayList<>();
        answer7.add(answers[7]);
        List<String> answer8 = new ArrayList<>();
        answer8.add(answers[8]);
        List<String> answer9 = new ArrayList<>();
        answer9.add(answers[9]);

        listHash.put(listDataHeader.get(0),answer0);
        listHash.put(listDataHeader.get(1),answer1);
        listHash.put(listDataHeader.get(2),answer2);
        listHash.put(listDataHeader.get(3),answer3);
        listHash.put(listDataHeader.get(4),answer4);
        listHash.put(listDataHeader.get(5),answer5);
        listHash.put(listDataHeader.get(6),answer6);
        listHash.put(listDataHeader.get(7),answer7);
        listHash.put(listDataHeader.get(8),answer8);
        listHash.put(listDataHeader.get(9),answer9);
    }
}
