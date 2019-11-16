package com.example.paijoov1.Home;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import ng.com.obkm.bottomnavviewwithfragments.R;
import com.example.paijoov1.R;

import com.example.paijoov1.Fragment_for_Main.ChatFragment;

import com.trendyol.bubblescrollbarlib.BubbleScrollBar;
import com.trendyol.bubblescrollbarlib.BubbleTextProvider;


public class HomeFragment extends Fragment  {

    private List<HomeItem> rv_list;
    private Spinner spinner;
    private ImageView image_box;

    private List <String> listData = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BubbleScrollBar scrollBar;
    private MyAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //image_box = getActivity().findViewById(R.id.image_box);
        //scaleImage(image_box);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(listData);

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                listData.get(position);
                Intent gotoChat = new Intent(getActivity(), ChatFragment.class);
                startActivity(gotoChat);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),layoutManager.getOrientation()));

        initData();
        Collections.sort(listData);

        recyclerView.setAdapter(new MyAdapter(listData));

        scrollBar = view.findViewById(R.id.bubble_scroll);
        scrollBar.attachToRecyclerView(recyclerView);
        scrollBar.setBubbleTextProvider(new BubbleTextProvider() {
            @Override
            public String provideBubbleText(int i) {

                return new StringBuilder(listData.get(i).substring(0,1)).toString();
            }
        });






        return view;
    }

    private void initData() {
        listData.add("Krane");
        listData.add("Bob");
        listData.add("Toon");
        listData.add("Orn");
        listData.add("Candy");
        listData.add("Pure");
        listData.add("Chin");
        listData.add("Pond");
        listData.add("Thri");
        listData.add("Dech");
    }


}
