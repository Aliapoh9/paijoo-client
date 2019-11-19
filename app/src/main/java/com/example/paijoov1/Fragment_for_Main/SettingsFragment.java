package com.example.paijoov1.Fragment_for_Main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.paijoov1.Login_Screen.NewLoginActivity;
import com.example.paijoov1.PinLock.SetPinLock;
import com.example.paijoov1.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Button logout_btn;

    private  Switch switch2;
    private  Switch password_switch;
    public boolean theme_check;
    public int count;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switch2 = view.findViewById(R.id.switch2);
        password_switch = view.findViewById(R.id.switch3);
        logout_btn = view.findViewById(R.id.logout_btn);

        SharedPreferences check_for_pin = getActivity().getSharedPreferences("check_for_pin",MODE_PRIVATE);
        String check_pin = check_for_pin.getString("checked","");


        SharedPreferences preferences =getActivity().getSharedPreferences("theme",MODE_PRIVATE);
        String theme = preferences.getString("mode","");
        if (theme.equals("true")) {
            switch2.setChecked(true);
        }
        else{
            switch2.setChecked(false);
        }

        if (check_pin.equals("true")) {
            password_switch.setChecked(true);
        }
        else if(check_pin.equals("false")){
            password_switch.setChecked(false);
        }


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.commit();

                SharedPreferences check_for_pin = getActivity().getSharedPreferences("check_for_pin",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = check_for_pin.edit();
                editor2.putString("pin","false");
                editor2.commit();

                password_switch.setChecked(false);

                Intent intent = new Intent(getActivity(), NewLoginActivity.class);
                startActivity(intent);
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    SharedPreferences preferences = getActivity().getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("mode","true");
                    editor.commit();
                    restartApp();



                } else {
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    SharedPreferences preferences = getActivity().getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("mode","false");
                    editor.commit();

                    restartApp(); //testing
                }
            }
        });
        password_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreferences check_for_pin = getActivity().getSharedPreferences("check_for_pin",MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = check_for_pin.edit();
                    editor2.putString("checked","true");
                    editor2.commit();
                    //restartApp();
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent set_pinlock = new Intent(getActivity().getApplicationContext(), SetPinLock.class);
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    startActivity(set_pinlock);


                } else {
                    SharedPreferences check_for_pin = getActivity().getSharedPreferences("check_for_pin",MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = check_for_pin.edit();
                    editor2.putString("checked","false");
                    editor2.commit();
                    restartApp();
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                }
            }
        });



        return view;
    }
    public void restartApp(){
        Intent restart = new Intent(getActivity().getApplicationContext(),MainActivity.class);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        startActivity(restart);

    }


}
