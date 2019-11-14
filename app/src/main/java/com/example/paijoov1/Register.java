package com.example.paijoov1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private TextInputLayout r_email;
    private TextInputLayout r_name;
    private TextInputLayout r_password;
    private TextView temp;

    private final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //FirebaseApp.initializeApp(this);
        r_email = findViewById(R.id.text_input_email);
        r_name = findViewById(R.id.text_input_username);
        r_password = findViewById(R.id.text_input_password);

        String emailInput = r_email.getEditText().getText().toString().trim();
        temp = findViewById(R.id.textView3);
        temp.setText(emailInput);

        configureNextButton();
    }

    private void configureNextButton() {
        Button nextButton = findViewById(R.id.confirm);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,chatActivity.class));
            }
        });
    }


    private boolean validateEmail() {

        String emailInput = r_email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            r_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            r_email.setError("Please enter a valid email address");
            return false;
        } else {
            r_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = r_password.getEditText().getText().toString().trim();
        System.out.println(passwordInput);

        if (passwordInput.isEmpty()) {
            r_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            r_password.setError("Password too weak");
            return false;
        } else {
            r_password.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = r_name.getEditText().getText().toString().trim();
        //String temp = usernameInput;

        if (usernameInput.isEmpty()) {
            r_name.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            r_name.setError("Username too long");
            return false;
        } else {
            r_name.setError(null);
            return true;
        }

    }

    public void confirmInput(View v) {
        final Intent test = new Intent(getApplicationContext(), chatActivity.class);
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            startActivity(test);
            return;
        }



        String input = "Email: " + r_email.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + r_name.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + r_password.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    public void test(View v){
        final Intent goto_login = new Intent(getApplicationContext(), chatActivity.class);

        startActivity(goto_login);
    }
}

