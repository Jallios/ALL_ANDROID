package com.example.messanger

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.messanger.model.Message
import com.example.messanger.utils.currentUid
import com.example.messanger.utils.database

class SingleRecycleAdapter (val context: Context, val listMessage : MutableList<Message>, val onClick : (Message?) -> Unit)
    : RecyclerView.Adapter<SingleRecycleAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.message_text);
        fun bind(user: Message?, onClick: (Message?) -> Unit) {
            itemView.setOnClickListener {
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageItem = listMessage[position]
        holder.text.setText(messageItem.text)
        var params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        if(messageItem.to != currentUid()){
            params.leftMargin = 500 // никакой ошибки
            holder.itemView.layoutParams = params
        }
        else{
            params.rightMargin = 500 // никакой ошибки
            holder.itemView.layoutParams = params
        }

        holder.bind(messageItem, onClick)

    }

    override fun getItemCount(): Int {
        return listMessage.size
    }
}