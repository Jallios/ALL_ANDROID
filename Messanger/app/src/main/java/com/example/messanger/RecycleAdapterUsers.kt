package com.example.messanger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.messanger.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso


class RecycleAdapterUsers(val context: Context, val listUsers : MutableList<User>,val onClick : (User?) -> Unit)
    : RecyclerView.Adapter<RecycleAdapterUsers.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.avatar);
        val nickname = view.findViewById<TextView>(R.id.nickname);
        val btn_add = view.findViewById<Button>(R.id.btn_add);
        fun bind(user: User?, onClick: (User?) -> Unit) {
            btn_add.setOnClickListener {
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageItem = listUsers[position]
        Picasso.get().load(messageItem.image).into(holder.avatar);
        holder.nickname.setText(messageItem.login);
        holder.bind(messageItem, onClick)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
}
interface Listener {
    fun follow(uid:String)
    fun unfollow(uid:String)
    fun click(uid:String)
}