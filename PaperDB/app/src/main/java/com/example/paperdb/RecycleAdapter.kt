package com.example.paperdb

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class RecycleAdapter(val context: Context, val listCars : MutableList<Car>, val onClick : (Car?) -> Unit)
    : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val CarTextView_name = view.findViewById<TextView>(R.id.cars_name_textView);
        val CarTextView_model = view.findViewById<TextView>(R.id.cars_model_textView);
        fun bind(car: Car?, onClick: (Car?) -> Unit) {
            itemView.setOnClickListener {
                onClick(car)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carItem = listCars[position]

        holder.CarTextView_name.setText(carItem.name);
        holder.CarTextView_model.setText(carItem.model);


        holder.bind(carItem, onClick)
    }

    override fun getItemCount(): Int {
        return listCars.size
    }


}