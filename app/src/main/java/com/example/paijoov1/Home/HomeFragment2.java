package com.example.paijoov1.Home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paijoov1.Add_friend.add_friend;
import com.example.paijoov1.Examples.ExampleAdapter;
import com.example.paijoov1.Examples.ExampleItem;
import com.example.paijoov1.R;
import com.example.paijoov1.Upload_Pic.getting_started;

import java.util.ArrayList;

//import ng.com.obkm.bottomnavviewwithfragments.R;
import com.example.paijoov1.Upload_Pic.getting_started;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment2 extends Fragment  {
    private ArrayList<ExampleItem> mExampleList;
    private Button add_friend_button;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CircleImageView circleImageView;
    private ImageView imageView5;


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
        mExampleList.add(new ExampleItem(R.drawable.boy, "Bob", "Back End Developer"));
        mExampleList.add(new ExampleItem(R.drawable.man, "Chin", "Front End Developer"));
        mExampleList.add(new ExampleItem(R.drawable.girl, "Rat", "Monopoly Expert"));
        mExampleList.add(new ExampleItem(R.drawable.girl, "Toon", "Monopoly Expert"));

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(mExampleList);

        add_friend_button = view.findViewById(R.id.add_friend_button);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        circleImageView = view.findViewById(R.id.circleImageView);




        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Log.d("test", "onItemClick: index = " + position);
                //Toast.makeText(getContext(),Log.d("test", "onItemClick: index = " + position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });

        add_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_addfriend = new Intent(v.getContext(), add_friend.class);
                startActivity(goto_addfriend);
            }
        });


        return view;
    }

}
