package com.example.apilast;

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

import com.example.apilast.models.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Film> FilmArrayList;
    private Intent intent;

    interface OnStateClickListener{
        void onStateClick(Film state, int position);
    }

    private final OnStateClickListener onClickListener;


    public RecycleAdapter(Context context, ArrayList<Film> FilmArrayList, OnStateClickListener onClickListener){
        this.context = context;
        this.FilmArrayList = FilmArrayList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Film film = FilmArrayList.get(position);
        holder.filmNameTV.setText(film.getNameRu());
        holder.ratingTV.setText(film.getRating());
        Picasso.with(context).load(film.getPosterUrlPreview()).into(holder.imgView);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onStateClick(film, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return FilmArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView filmNameTV, ratingTV;
        ImageView imgView;

        ViewHolder(View view) {
            super(view);
            filmNameTV = view.findViewById(R.id.film_textView);
            imgView = view.findViewById(R.id.img_film);
            ratingTV = view.findViewById(R.id.film_rating_textView);
        }

    }
}
