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
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        EditText content = findViewById(R.id.message_box);
        Message new_msg = new Message("User1", content.getText().toString());

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view7 = inflater.inflate(R.layout.my_message, null);

        TextView new_text = (TextView) view7.findViewById(R.id.message_body);
        new_text.setText(new_msg.get_content());

        msg_view.addView(view7);

        content.setText("");

    }

    public void sendMessage2(View view) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        EditText content = findViewById(R.id.message_box);
        Message new_msg = new Message("User2", content.getText().toString());

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view7 = inflater.inflate(R.layout.other_message, null);

        TextView new_text = (TextView) view7.findViewById(R.id.message_body);
        new_text.setText(new_msg.get_content());

        msg_view.addView(view7);

        content.setText("");

    }
}
