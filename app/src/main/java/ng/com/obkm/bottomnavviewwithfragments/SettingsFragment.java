package ng.com.obkm.bottomnavviewwithfragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.paijoov1.NewLoginActivity;
import com.example.paijoov1.R;
import ng.com.obkm.bottomnavviewwithfragments.SettingsActivity;
import static android.content.Context.MODE_PRIVATE;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Button logout_btn;

    private  Switch switch2;
    public boolean theme_check;
    public int count;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switch2 = view.findViewById(R.id.switch2);
        logout_btn = view.findViewById(R.id.logout_btn);


        SharedPreferences preferences =getActivity().getSharedPreferences("theme",MODE_PRIVATE);
        String theme = preferences.getString("mode","");
        if (theme.equals("true")) {
            switch2.setChecked(true);
        }
        else{
            switch2.setChecked(false);
        }

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.commit();
                Intent intent = new Intent(getActivity(), NewLoginActivity.class);
                startActivity(intent);
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    SharedPreferences preferences = getActivity().getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("mode","true");
                    editor.commit();
                    restartApp();



                } else {
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences preferences = getActivity().getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("mode","false");
                    editor.commit();
                    restartApp();

                }
            }
        });



        return view;
    }
    public void restartApp(){
        Intent restart = new Intent(getActivity().getApplicationContext(),MainActivity.class);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        startActivity(restart);

    }


}
