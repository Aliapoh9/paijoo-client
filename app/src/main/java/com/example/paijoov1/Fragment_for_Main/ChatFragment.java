package com.example.paijoov1.Fragment_for_Main;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Context;

import com.example.paijoov1.Chat_Chin.Message;
import com.example.paijoov1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    public Button send_1;
    public Button send_2;
    public LinearLayout msg_view;
    private Message[] messageHistory = {new Message("User1", "123"), new Message("User2", "hi there")};

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        //inflater = getLayoutInflater();

        send_1 = view.findViewById(R.id.send_1);
        send_2 = view.findViewById(R.id.send_2);
        msg_view = view.findViewById(R.id.msg_view);

        LinearLayout msg_view = (LinearLayout) view.findViewById(R.id.msg_view);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        send_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout msg_view = (LinearLayout) getActivity().findViewById(R.id.msg_view);
                final ScrollView scrl_view = (ScrollView)  getActivity().findViewById(R.id.scrl);
                EditText content =  getActivity().findViewById(R.id.message_box);
                Message new_msg = new Message("User1", content.getText().toString());

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        });

        send_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout msg_view = (LinearLayout) getActivity().findViewById(R.id.msg_view);
                final ScrollView scrl_view = (ScrollView) getActivity().findViewById(R.id.scrl);
                EditText content = getActivity().findViewById(R.id.message_box);
                Message new_msg = new Message("User2", content.getText().toString());

                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        });



        return view;


    }




}
