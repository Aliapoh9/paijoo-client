package com.example.paijoov1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat_from_home extends AppCompatActivity {

    private Message[] messageHistory = {new Message("User1", "12344"), new Message("User2", "hi there")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_from_home);
        loadMessagesHis();
       // loadHistory();
    }

    public void loadMessagesHis()
    {
        Retrofit rf = new Retrofit.Builder().baseUrl("https://paijoo-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PaijooService pService = rf.create(PaijooService.class);

        Call<ArrayList<Conversation>> call = pService.getMes(1);
        call.enqueue(new Callback<ArrayList<Conversation>>()
        {
             @Override
             public void onResponse(Call<ArrayList<Conversation>> call, Response<ArrayList<Conversation>> response) {
                 if (response.isSuccessful())
                 {
                     Log.d("debussy", "Success..");
                     ArrayList<Conversation> MessageMap = response.body();
                     Log.w("2.0 getFeed > Full json res wrapped in pretty printed gson => ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
                     messageHistory[0].setContent(MessageMap.get(0).getMessages().get(0).getContent().getContent());
                 }
                 else
                 {
                     Log.d("debussy", "400..");
                     messageHistory[0].setContent(Integer.toString(response.code()));
                 }
                 loadHistory();
             }

             @Override
             public void onFailure(Call<ArrayList<Conversation>> call, Throwable t) {
                 Log.d("debussy", "Failed" + t.getMessage() );
                 messageHistory[0].setContent("Error.");
                 loadHistory();
             }
         });

    }



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
