package ng.com.obkm.bottomnavviewwithfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.example.paijoov1.R;

import ng.com.obkm.bottomnavviewwithfragments.MainActivity;
import com.example.paijoov1.NewLoginActivity;

public class SettingsActivity extends AppCompatActivity {

    Button button8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button8 = findViewById(R.id.button8);


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.commit();

                //finish(); used to exit app

                Intent intent = new Intent(getApplicationContext(),NewLoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
