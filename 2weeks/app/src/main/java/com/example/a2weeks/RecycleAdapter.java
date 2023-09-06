package com.example.a2weeks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cars> carsArrayList;
    private Intent intent;

    public RecycleAdapter(Context context, ArrayList<Cars> carsArrayList){
        this.context = context;
        this.carsArrayList = carsArrayList;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cars cars = carsArrayList.get(position);
        holder.carsNameTV.setText(cars.getModelCars());
        holder.carsImageView.setBackgroundResource(cars.getImgCars());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( context,cars.getModelCars(), Toast.LENGTH_SHORT).show();
                intent = new Intent(context, Info.class);
                intent.putExtra("item", cars);
                context.startActivity(intent);
        }
        });

    }

    @Override
    public int getItemCount() {
        return carsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView carsNameTV;
        ImageView carsImageView;

        ViewHolder(View view) {
            super(view);
            carsNameTV = view.findViewById(R.id.cars_textView);
            carsImageView = view.findViewById(R.id.cars_imageView);
        }

    }
}
