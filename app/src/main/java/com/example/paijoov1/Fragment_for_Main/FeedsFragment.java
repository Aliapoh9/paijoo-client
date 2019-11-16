package com.example.paijoov1.Fragment_for_Main;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.paijoov1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment {


    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        return view;
    }

}
