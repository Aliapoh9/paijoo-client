package com.example.paijoov1.Home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paijoov1.Examples.ExampleAdapter;
import com.example.paijoov1.Examples.ExampleItem;
import com.example.paijoov1.R;

import java.util.ArrayList;

//import ng.com.obkm.bottomnavviewwithfragments.R;


public class HomeFragment2 extends Fragment  {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public HomeFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        //image_box = getActivity().findViewById(R.id.image_box);
        //scaleImage(image_box);

        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_account_circle_black_24dp, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_account_circle_black_24dp, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_account_circle_black_24dp, "Line 5", "Line 6"));

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(mExampleList);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {

            }
        });
        return view;
    }

}
