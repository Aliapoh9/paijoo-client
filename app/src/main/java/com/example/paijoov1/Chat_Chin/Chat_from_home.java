package com.example.paijoov1.Chat_Chin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paijoov1.Cache;
import com.example.paijoov1.Conversation;
import com.example.paijoov1.PaijooService;
import com.example.paijoov1.R;
import com.example.paijoov1.SocketClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat_from_home extends AppCompatActivity {

    private ArrayList<Conversation> convoList = new ArrayList<Conversation>();
    private Retrofit rf;
    private PaijooService pService;
    private SocketClient SocketC = new SocketClient();
    private ArrayList<Conversation.Messages> messagesFromSocket = new ArrayList<Conversation.Messages>();
    private int mfsInt = 0;
    private int chatIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_from_home);
        TextView headerTitle = findViewById(R.id.topBar);
        headerTitle.setText("Bey");

        rf = new Retrofit.Builder().baseUrl("https://paijoo-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        pService = rf.create(PaijooService.class);
        loadMessagesHis();
        chatIndex = getIntent().getIntExtra("chatIndex", 0);
        Log.d("POS", Integer.toString(chatIndex));
        SocketC.createWebSocketClient(2, messagesFromSocket, this);
        //Log.d("Position", Integer.toString(getIntent().getIntExtra("chatIndex", 0)));
    }

    public void saveToCache(String key, Object ob)
    {
        try {
            Cache.writeObject(this, key, ob);
            Log.d("SAVE-COMPLETE", "saved object with the key: " + key);
        }
        catch (IOException e)
        {
            Log.e("SAVE-ERROR", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("SAVE-ERROR", e.getMessage());
        }
    }


    public void loadConversation(int cID) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Conversation cToLoad = convoList.get(cID);
        for (Conversation.Messages msg : cToLoad.getMessages())
        {
            View new_msg_view;
            if (msg.getAuthor_id() != 1)
            {
                new_msg_view = inflater.inflate(R.layout.other_message, null);
                TextView new_text = new_msg_view.findViewById(R.id.message_body);
                TextView timeStamp = new_msg_view.findViewById(R.id.time_view);
                String parsedTime = msg.getCreated_at().split("T")[1].substring(0, 5);
                timeStamp.setText(parsedTime);
                new_text.setText(msg.getContent().getContent());
            }
            else {
                new_msg_view = inflater.inflate(R.layout.my_message, null);
                TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
                TextView setRead = new_msg_view.findViewById(R.id.read_view);
                TextView timeStamp = new_msg_view.findViewById(R.id.time_view);

                if (!msg.getSeen())
                    setRead.setVisibility(View.INVISIBLE);
                timeStamp.setText(msg.getCreated_at());
                new_text.setText(msg.getContent().getContent());
            }
            msg_view.addView(new_msg_view);
        }
    }

    public void loadMessagesHis()
    {
        try {
            convoList.add((Conversation) Cache.readObject(this, Integer.toString(chatIndex)));
            Log.d("cache-success", "success!!!");
            loadConversation(chatIndex);
        }
        catch (IOException e)
        {
            Log.e("cache-failureIO", e.getMessage());

            Call<ArrayList<Conversation>> call = pService.getMes(chatIndex + 1);
            call.enqueue(new Callback<ArrayList<Conversation>>()
            {
                @Override
                public void onResponse(Call<ArrayList<Conversation>> call, Response<ArrayList<Conversation>> response) {
                    if (response.isSuccessful())
                    {
                        Log.d("debussy", "Who dares, wins");
                        convoList = response.body();
                        for (Conversation c : convoList)
                        {
                            saveToCache(Integer.toString(c.getConversationId()-1), c);
                        }
                        // Log.w("2.0 getFeed > Full json res wrapped in pretty printed gson => ",new GsonBuilder().setPrettyPrinting().create().toJson(response));
                        //messageHistory[0].setContent(MessageMap.get(0).getMessages().get(0).getContent().getContent());
                        loadConversation(chatIndex);
                    }
                    else
                    {
                        Log.d("debussy", "400..");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Conversation>> call, Throwable t) {
                    Log.d("debussy", "Failed" + t.getMessage() );
                }
            });
        }
        catch (Exception e)
        {
            Log.e("cache-failureOTHER", e.getMessage());
        }

    }
    public void update()
    {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        final ScrollView scrl_view = (ScrollView) findViewById(R.id.scrl);
        EditText content = findViewById(R.id.message_box);

        if (messagesFromSocket.size() > 0) {
            Conversation.Messages new_msg = messagesFromSocket.get(mfsInt);
            mfsInt += 1;

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View new_msg_view = inflater.inflate(R.layout.my_message, null);

            TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
            TextView setTime = new_msg_view.findViewById(R.id.time_view);
            String currentTime = Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                    + ":" + Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
            new_text.setText(new_msg.getContent().getContent());
            setTime.setText(currentTime);

            Log.d("MessageIN", new_msg.getContent().getContent());

            msg_view.addView(new_msg_view);
        }
    }



    public void sendMessage(View view) {
        EditText check = findViewById(R.id.message_box);
        if (!check.getText().toString().trim().isEmpty()) {
            LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
            final ScrollView scrl_view = (ScrollView) findViewById(R.id.scrl);
            EditText content = findViewById(R.id.message_box);
            Message new_msg = new Message("User1", content.getText().toString());

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View new_msg_view = inflater.inflate(R.layout.my_message, null);

            TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
            TextView setTime = new_msg_view.findViewById(R.id.time_view);
            String currentTime = Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                    + ":" + Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
            new_text.setText(new_msg.get_content());
            setTime.setText(currentTime);

            msg_view.addView(new_msg_view);

            content.setText("");
            Conversation.Messages new_messages = new Conversation.Messages(1, 1, 2, 1, new Conversation.Messages.TextContent(1, new_msg.get_content()), false, false,
                    currentTime, chatIndex);
            SocketC.sendMessage(new_messages);


            // Scroll the scroll view down
            scrl_view.post(new Runnable() {
                public void run() {
                    scrl_view.fullScroll(scrl_view.FOCUS_DOWN);
                }
            });
        }

    }

    public void sendMessage2(View view) {
        LinearLayout msg_view = (LinearLayout) findViewById(R.id.msg_view);
        final ScrollView scrl_view = (ScrollView) findViewById(R.id.scrl);
        EditText content = findViewById(R.id.message_box);
        Message new_msg = new Message("User2", content.getText().toString());

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View new_msg_view = inflater.inflate(R.layout.other_message, null);

        TextView new_text = (TextView) new_msg_view.findViewById(R.id.message_body);
        TextView setTime = new_msg_view.findViewById(R.id.time_view);
        String currentTime = Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                + ":" + Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
        new_text.setText(new_msg.get_content());
        setTime.setText(currentTime);

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
