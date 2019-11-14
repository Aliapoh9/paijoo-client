package com.example.paijoov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import ng.com.obkm.bottomnavviewwithfragments.MainActivity;

public class First_screen extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 1000;
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_welcome_screen);
    //preferences = getApplicationContext().getSharedPreferences("logged", MODE_PRIVATE);
    //SharedPreferences.Editor editor = preferences.edit();
    //editor.putBoolean("logged",false);
    //editor.commit();

    //sp3 = getSharedPreferences("logged",MODE_PRIVATE);
    //sp3.edit().putBoolean("logged",false).commit();
    //if(preferences.getBoolean("logged",false)){
    //Toast.makeText(this,"First Page false",Toast.LENGTH_SHORT).show();
    //gotoHomeActivity();
    //}
    //else{
    //Toast.makeText(this,"First Page true",Toast.LENGTH_SHORT).show();
    //gotoMainActivity();
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        AnimationUtils.loadAnimation(this, R.anim.bounce);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(getApplicationContext(), homeActivity.class);
                startActivity(homeIntent);
                finish();


            }
        }, SPLASH_TIME_OUT);
    }



    public void gotoHomeActivity(){
        Intent i = new Intent(this,homeActivity.class);
        startActivity(i);
    }

    public void gotoMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


}


