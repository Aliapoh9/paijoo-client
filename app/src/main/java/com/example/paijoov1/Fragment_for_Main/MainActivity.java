package com.example.paijoov1.Fragment_for_Main;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.paijoov1.R;

import android.view.MenuItem;


import com.example.paijoov1.Home.HomeFragment2;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

public class MainActivity extends AppCompatActivity {


    //final Fragment fragment1 = new HomeFragment();
    final Fragment fragment1 = new HomeFragment2();
    final Fragment fragment2 = new ChatFragment();
    final Fragment fragment3 = new FeedsFragment();
    final Fragment fragment4 = new SettingsFragment();
    //final Fragment fragment4 = new Settings_demo();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences =getSharedPreferences("theme",MODE_PRIVATE);
        String theme = preferences.getString("mode","");
        if (theme.equals("true")) {
            AppCompatDelegate.setDefaultNightMode(
                    MODE_NIGHT_YES);
            //Toast.makeText(getApplicationContext(),"Wrong?",Toast.LENGTH_SHORT).show();
        }
        else{
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();
        fm.beginTransaction().add(R.id.main_container,fragment4,"4").hide(fragment4).commit();



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    // fm.beginTransaction().replace(R.id.main_container,fragment1).addToBackStack(null).commit();
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:

                    //fm.beginTransaction().replace(R.id.main_container,fragment2).addToBackStack(null).commit();
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_notifications:
                    //fm.beginTransaction().remove(fragment4).commit();
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
                case R.id.navigation_feed:



                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    return true;
            }
            return false;
        }
    };




    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }




}
