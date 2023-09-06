package com.example.sqllite;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cars> carsArrayList;
    Intent intent;


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
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        Cars cars = carsArrayList.get(position);
        holder.carsModel.setText(cars.getModel_Car());
        holder.carsOpisanie.setText(cars.getOpisanie_Car());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SecondActivity.class);

                // below we are passing all our values.
                i.putExtra("ID", cars.getID_Car());
                i.putExtra("model", cars.getModel_Car());
                i.putExtra("opisanie", cars.getOpisanie_Car());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView carsModel, carsOpisanie;
        Button delete;

        ViewHolder(View view) {
            super(view);
            carsModel = view.findViewById(R.id.c_model);
            carsOpisanie = view.findViewById(R.id.c_opisanie);
            delete = view.findViewById(R.id.delete_car);
        }

    }
}

