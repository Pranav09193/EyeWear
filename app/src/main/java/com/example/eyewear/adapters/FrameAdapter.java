package com.example.eyewear.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eyewear.FrameDetailsActivity;
import com.example.eyewear.R;
import com.example.eyewear.model.FrameData;

import java.util.List;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.MyViewHolder> {

    List<FrameData> imageDataList;
    Context context;

    public FrameAdapter(Context context, List<FrameData> imageDataList) {
        this.imageDataList = imageDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_frame, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {

        Glide.with(context).load(imageDataList.get(position).getImageUrl()).into(holder.imageView);
        holder.name.setText(imageDataList.get(position).getName());
        holder.price.setText(imageDataList.get(position).getPrice()+"");
        holder.rating.setText(imageDataList.get(position).getRating());

        String name = imageDataList.get(position).getName();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, FrameDetailsActivity.class);
                i.putExtra("detail",imageDataList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.frameImage);
            name = itemView.findViewById(R.id.frameName);
            price = itemView.findViewById(R.id.framePrice);
            rating = itemView.findViewById(R.id.frameRating);

        }
    }
}
