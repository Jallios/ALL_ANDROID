package com.example.firebase

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
import com.example.firebase.R
import com.example.firebase.models.Notes


class RecycleAdapter(val context: Context, val listNotes : MutableList<Notes>, val onClick : (Notes?) -> Unit)
    : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val notes_header_textView = view.findViewById<TextView>(R.id.notes_header_textView);
        fun bind(notes: Notes?, onClick: (Notes?) -> Unit) {
            itemView.setOnClickListener {
                onClick(notes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notesItem = listNotes[position]

        holder.notes_header_textView.setText(notesItem.header);

        holder.bind(notesItem, onClick)
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }


}