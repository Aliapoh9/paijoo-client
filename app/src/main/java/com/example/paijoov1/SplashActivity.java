package com.example.paijoov1;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paijoov1.Login_Screen.NewLoginActivity;

/**
 * Created by ssaurel on 02/12/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, NewLoginActivity.class);
        startActivity(intent);
        //finish();
    }
}