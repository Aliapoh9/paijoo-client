package com.example.paijoov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.MainActivity;

public class SetPinLock extends AppCompatActivity implements PatternLockViewListener {

    PatternLockView patternLockView;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin_lock);
        patternLockView = findViewById(R.id.pattern_lock_view);
        patternLockView.addPatternLockListener(this);
        SharedPreferences pinlock = getSharedPreferences("check_pin",MODE_PRIVATE);
        String check_pin = pinlock.getString("password","");

        SharedPreferences check_for_pin = getSharedPreferences("check_for_pin",MODE_PRIVATE);
        String pin = pinlock.getString("pin","");


    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        temp = PatternLockUtils.patternToString(patternLockView,pattern);
        Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();

        SharedPreferences pinlock = getSharedPreferences("check_pin", MODE_PRIVATE);
        SharedPreferences.Editor editor = pinlock.edit();
        editor.putString("password",temp);
        editor.commit();

        SharedPreferences check_for_pin = getSharedPreferences("check_for_pin",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = check_for_pin.edit();
        editor2.putString("pin","true");
        editor2.commit();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        //if(PatternLockUtils.patternToString(patternLockView,pattern).equalsIgnoreCase("0123")){

           // patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
           // Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
       // }
       // else{
        //    patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
         //   Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    public void onCleared() {

    }



}
