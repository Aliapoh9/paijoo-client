package com.example.paijoov1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.design.widget.TextInputLayout;
//import android.widget.LinearLayout;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.paijoov1.Upload_Pic.getting_started;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class test_register extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=!])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    SharedPreferences sp2;
    public SharedPreferences preferences;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_register);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEmail() && validateUsername() && validatePassword()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.commit();


                    Intent intent = new Intent(getApplicationContext(), getting_started.class);
                    startActivity(intent);
                }

                String input = "Email: " + textInputEmail.getEditText().getText().toString();
                input += "\n";
                input += "Username: " + textInputUsername.getEditText().getText().toString();
                input += "\n";
                input += "Password: " + textInputPassword.getEditText().getText().toString();

            }
        });

    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();


        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            //Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput() {

        if (validateEmail() && validateUsername() && validatePassword()) {

            //Toast.makeText(this, "Set to logged in", Toast.LENGTH_SHORT).show();
            //sp2.edit().putBoolean("logged",true).commit();

           //preferences = getApplicationContext().getSharedPreferences("logged", MODE_PRIVATE);
            //SharedPreferences.Editor editor = preferences.edit();
            //editor.putBoolean("logged",true);
            //editor.commit();

            //if(preferences.getBoolean("logged",true)){
                //Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
            //}
            //Intent main_menu = new Intent(getApplicationContext(),MainActivity.class);
            //startActivity(main_menu);

        }

        //startActivity(main_menu);
        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + textInputUsername.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getEditText().getText().toString();


        /*
        try {
            Intent main_menu = new Intent(this,MainActivity.class);
            startActivity(main_menu);
        }catch (Exception e){
            System.out.println(e);
        }
       //Toast.makeText(this, input, Toast.LENGTH_SHORT).show();


         */
    }




}

