package com.example.phil.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.phil.myapplication.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    TextView txt_title;
    ImageView image;
    LinearLayout parent_Lineare;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_title = (TextView)itemView.findViewById(R.id.txt_title);
        image = (ImageView) itemView.findViewById(R.id.image);
        parent_Lineare = (LinearLayout) itemView.findViewById(R.id.parent_layout);
    }
}