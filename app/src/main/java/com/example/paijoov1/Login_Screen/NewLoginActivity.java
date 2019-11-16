package com.example.paijoov1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import ng.com.obkm.bottomnavviewwithfragments.SettingsActivity;
import ng.com.obkm.bottomnavviewwithfragments.MainActivity;

public class NewLoginActivity extends AppCompatActivity {


    private  TextInputEditText email;
    private TextInputEditText password;
    String password_input;
    String email_input;
    private CheckBox remember;
    private Button login;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_UNSPECIFIED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        email = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        remember = findViewById(R.id.remember);
        login =  findViewById(R.id.login);
        register_btn = findViewById(R.id.register_btn);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");

        SharedPreferences pinlock = getSharedPreferences("check_pin",MODE_PRIVATE);
        String check_pin = pinlock.getString("password","");

        SharedPreferences check_for_pin = getSharedPreferences("check_for_pin",MODE_PRIVATE);
        String pin = check_for_pin.getString("checked","");

        if (checkbox.equals("true") && check_pin.equals("true")) {
            Toast.makeText(this,"Enter with Password",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (this,PinLock.class);
            startActivity(intent);
            //finish();
            //Toast.makeText(getApplicationContext(),"Wrong?",Toast.LENGTH_SHORT).show();
        }
        else if (checkbox.equals("true") && pin.equals("false")){
            Intent intent = new Intent (this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"No Password",Toast.LENGTH_SHORT).show();
        }


        else if (checkbox.equals("false")){
            Toast.makeText(getApplicationContext(),"Please sign in",Toast.LENGTH_SHORT).show();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_menu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main_menu);

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(getApplicationContext(),test_register.class);
                startActivity(regis);
            }
        });

       remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (buttonView.isChecked() ){
                   SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                   SharedPreferences.Editor editor = preferences.edit();
                   editor.putString("remember","true");
                   editor.commit();
                   Toast.makeText(getApplicationContext(),"Checked",Toast.LENGTH_SHORT).show();
               }else if (!buttonView.isChecked()){
                   SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                   SharedPreferences.Editor editor = preferences.edit();
                   editor.putString("remember","false");
                   editor.commit();
                   Toast.makeText(getApplicationContext(),"Unchecked",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }



    public void check(View view){

        //Toast.makeText(this, "gotoChat", Toast.LENGTH_SHORT).show();
        email_input = email.getText().toString().trim();
        password_input = password.getText().toString().trim();

        if (test_login() == true && test_password() == true){
            Toast.makeText(this,"Set to True",Toast.LENGTH_SHORT).show();
            //sp1.edit().putBoolean("logged",true).commit();
            //preferences = getApplicationContext().getSharedPreferences("logged", MODE_PRIVATE);
            //SharedPreferences.Editor editor = preferences.edit();
            //editor.putBoolean("logged",true);
            //editor.commit();
            startActivity(new Intent(this,MainActivity.class));
            finish();

        }
        else{
            String temp;
            temp = email_input;
            Toast.makeText(this, temp ,Toast.LENGTH_SHORT).show();
            test_login();
            //Toast.makeText(this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();


        }

    }

    private boolean test_login(){
        email_input = email.getText().toString().trim();
        password_input = password.getText().toString().trim();

        if (email_input.isEmpty()) {

            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email_input).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            //Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private boolean test_password() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        }else {
            password.setError(null);
            return true;
        }
    }


    //else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
    //            password.setError("Password too weak");
    //            return false;
    //        }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }



}
