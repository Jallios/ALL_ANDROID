package com.example.messanger

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.messanger.model.User
import com.squareup.picasso.Picasso

class RecycleAdapterFriends(val context: Context, val listUsers : MutableList<User>, val onClick : (User?) -> Unit)
    : RecyclerView.Adapter<RecycleAdapterFriends.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.avatar_friend);
        val nickname = view.findViewById<TextView>(R.id.nickname_friend);
        val btn_delete = view.findViewById<Button>(R.id.btn_delete);
        fun bind(user: User?, onClick: (User?) -> Unit) {
            btn_delete.setOnClickListener {
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_friends, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageItem = listUsers[position]
        Picasso.get().load(messageItem.image).into(holder.avatar);
        holder.nickname.setText(messageItem.login)
        holder.bind(messageItem, onClick)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("uid", messageItem.uid)
            startActivity(context,intent,null)
        }

    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
}