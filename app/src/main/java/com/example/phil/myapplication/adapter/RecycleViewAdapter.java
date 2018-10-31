package com.example.phil.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Toast;
import com.example.phil.myapplication.R;

import com.example.phil.myapplication.SecondeActivity;
import com.example.phil.myapplication.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private Context context;
    List<Model> postList;

    public RecycleViewAdapter(Context context, List<Model> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup  parent , int i) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_items, parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position) {

        holder.txt_title.setText(String.valueOf(postList.get(position).getName()));
        Picasso.with(context)
                .load(postList.get(position).getImage())
                .into(holder.image);
        holder.parent_Lineare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,postList.get(position).getName(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SecondeActivity.class);
               // intent.putExtra("image",postList.get(position).getImage());
                intent.putExtra("name",postList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}

