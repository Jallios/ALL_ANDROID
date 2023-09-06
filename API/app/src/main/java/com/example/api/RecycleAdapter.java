package com.example.api;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Car> carsArrayList;
    private Intent intent;

    public RecycleAdapter(Context context, ArrayList<Car> carsArrayList){
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
        Car cars = carsArrayList.get(position);
        holder.carsNameTV.setText(cars.getCar_Name());
        Picasso.with(context).load(cars.getCar_Img()).into(holder.imgView);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,cars.getCar_Name(),Toast.LENGTH_LONG).show();
                intent = new Intent(context.getApplicationContext(), Info.class);
                intent.putExtra("item", cars.getId_Car());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        ImageView imgView;

        ViewHolder(View view) {
            super(view);
            carsNameTV = view.findViewById(R.id.cars_textView);
            imgView = view.findViewById(R.id.img_car);
        }

    }
}
