package com.example.paijoov1;

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.ChatFragment;
import ng.com.obkm.bottomnavviewwithfragments.MainActivity;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public View view;


    List<String> listData;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyAdapter(List<String> listData){
        this.listData = listData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.setItem(position);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

   

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_name;
        ImageView image_view5;
        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name);



        }


        public void setItem(int position) {

        }

        @Override
        public void onClick(View v) {
            if(mListener!=null)
                mListener.onItemClick(getAdapterPosition());
            v.getContext().startActivity(new Intent(v.getContext(), ChatFragment.class));

        }
    }


}
