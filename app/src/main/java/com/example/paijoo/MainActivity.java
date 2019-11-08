package com.example.paijoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadHistory();
    }

    private Message[] messageHistory = {new Message("User1", "hello"), new Message("User2", "hi there")};

    public void loadHistory()
    {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Message msg : messageHistory)
        {
            View new_msg_view = null;
            if (msg.get_sender() != "User1")
            {
                new_msg_view = inflater.inflate(R.layout.other_message, null);
                TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
                new_text.setText(msg.get_content());
            }
            else
            {
                new_msg_view = inflater.inflate(R.layout.my_message, null);
                TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
                new_text.setText(msg.get_content());
            }

            msg_view.addView(new_msg_view);

        }
    }

    public void sendMessage(View view) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        final ScrollView scrl_view = (ScrollView) findViewById(R.id.scrl);
        EditText content = findViewById(R.id.message_box);
        Message new_msg = new Message("User1", content.getText().toString());

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View new_msg_view = inflater.inflate(R.layout.my_message, null);

        TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
        new_text.setText(new_msg.get_content());

        msg_view.addView(new_msg_view);

        content.setText("");

        // Scroll the scroll view down
        scrl_view.post(new Runnable() {
            public void run() {
                scrl_view.fullScroll(scrl_view.FOCUS_DOWN);
            }
        });

    }

    public void sendMessage2(View view) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        final ScrollView scrl_view = (ScrollView) findViewById(R.id.scrl);
        EditText content = findViewById(R.id.message_box);
        Message new_msg = new Message("User2", content.getText().toString());

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View new_msg_view = inflater.inflate(R.layout.other_message, null);

        TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
        new_text.setText(new_msg.get_content());

        msg_view.addView(new_msg_view);

        content.setText("");

        // Scroll the scroll view down
        scrl_view.post(new Runnable() {
            public void run() {
                scrl_view.fullScroll(scrl_view.FOCUS_DOWN);
            }
        });

    }
}
