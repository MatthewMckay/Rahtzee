package com.matthewmckay.rahtzee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChatActivity extends AppCompatActivity {
    //private List<Message> messages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if (findViewById(R.id.main_chat_view) != null){
            if (savedInstanceState != null){
                return;
            }
            MsgFragment firstFragment = new MsgFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.main_chat_view, firstFragment).commit();
        }
    }


}
