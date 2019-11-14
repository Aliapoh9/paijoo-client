package com.example.paijoov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ng.com.obkm.bottomnavviewwithfragments.MainActivity;

public class homeActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
        //setContentView(R.layout.activity_main);






    }


    public void gotoLogin(View view) {
        Toast.makeText(this, "gotoLogin", Toast.LENGTH_SHORT).show();
        Intent gotoIntent = new Intent(this,NewLoginActivity.class); //Need to do new login

        startActivity(gotoIntent);
    }

    public void gotoRegister(View view) {
        Intent regisIntent = new Intent(this,test_register.class);
        startActivity(regisIntent);
    }

    /*
    public void demo (View view){
        Toast.makeText(this, "demo", Toast.LENGTH_SHORT).show();
        Intent demoIntent = new Intent(this,MainActivity.class);
        startActivity(demoIntent);
    }

     */


}
