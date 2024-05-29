package com.example.eyewear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eyewear.R;
import com.example.eyewear.model.CartData;
import com.example.eyewear.model.FrameData;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<CartData> cartDataList;
    Context context;

    public CartAdapter(List<CartData> imageDataList, Context context) {
        this.cartDataList = imageDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        Glide.with(context).load(cartDataList.get(position).getImageUrl()).into(holder.cartItemImage);
        holder.name.setText(cartDataList.get(position).getName());
        holder.price.setText(cartDataList.get(position).getPrice());
        holder.timeDate.setText(cartDataList.get(position).getCurrentTime()+" "+cartDataList.get(position).getCurrentDate());

    }

    @Override
    public int getItemCount() {
        return cartDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cartItemImage;
        TextView name, price, timeDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemImage=itemView.findViewById(R.id.cartItemImage);
            name=itemView.findViewById(R.id.cartItemName);
            price=itemView.findViewById(R.id.cartItemPrice);
            timeDate=itemView.findViewById(R.id.cartItemDateTime);

        }
    }
}
