package com.example.paijoov1.PinLock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.paijoov1.R;

import java.util.List;

import com.example.paijoov1.Fragment_for_Main.MainActivity;

public class PinLock extends AppCompatActivity implements PatternLockViewListener {

    PatternLockView patternLockView2;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lock);
        patternLockView2 = findViewById(R.id.pattern_lock_view2);
        patternLockView2.addPatternLockListener(this);
        SharedPreferences pinlock = getSharedPreferences("check_pin",MODE_PRIVATE);
        String check_pin = pinlock.getString("password","");


    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        //temp = PatternLockUtils.patternToString(patternLockView,pattern);
        //Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();
        //SharedPreferences pinlock = getSharedPreferences("check_pin", MODE_PRIVATE);
        //SharedPreferences.Editor editor = pinlock.edit();
        //editor.putString("password",temp);
        //editor.commit();
        SharedPreferences pinlock = getSharedPreferences("check_pin",MODE_PRIVATE);
        String check_pin = pinlock.getString("password",temp);


        if(PatternLockUtils.patternToString(patternLockView2,pattern).equalsIgnoreCase(check_pin)){

            patternLockView2.setViewMode(PatternLockView.PatternViewMode.CORRECT);
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (this,MainActivity.class);
            startActivity(intent);
        }
        else{
            patternLockView2.setViewMode(PatternLockView.PatternViewMode.WRONG);
           Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onCleared() {

    }



}
