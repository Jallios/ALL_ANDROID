package com.example.messanger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messanger.model.User
import com.example.messanger.utils.currentUid
import com.example.messanger.utils.database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class MessageActivity : AppCompatActivity() {

    lateinit var text: EditText
    lateinit var imageView: ImageView
    lateinit var messageRV:RecyclerView
    lateinit var adapter: SingleRecycleAdapter
    private val messageItemsList : MutableList<com.example.messanger.model.Message> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        text = findViewById(R.id.message)
        imageView = findViewById(R.id.btn_send)
        messageRV = findViewById(R.id.Single_Message_rv)

        val userUID = intent.getStringExtra("uid").toString()

        imageView.setOnClickListener {
            sendMessage(text.text.toString().trim(), userUID, "")
            text.setText("")
        }

        database.child("messages").child(currentUid()!!).child(userUID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (it in dataSnapshot.children) {
                    messageItemsList.add(com.example.messanger.model.Message(it.child("text").value.toString(),it.child("type").value.toString(),
                        it.child("to").value.toString(),it.child("timeStamp").value.toString()))

                }
                Log.d("mian",messageItemsList.toString())
                setUpAdapter(messageItemsList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })


    }




    private fun sendMessage(message:String, rUid:String, typeText:String) {
        var refdialogUser = "messages/${currentUid()}/${rUid}"
        var refreceiveddialogUser = "messages/${rUid}/${currentUid()}"
        val messageKey = database.child(refdialogUser).push().key

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        val mapMessage = "$refdialogUser/$messageKey"
        val mapMessage2 = "$refreceiveddialogUser/$messageKey"


        database.child(mapMessage).setValue(com.example.messanger.model.Message(message,typeText,rUid,currentDate))
        database.child(mapMessage2).setValue(com.example.messanger.model.Message(message,typeText,rUid,currentDate))
        messageItemsList.clear()
    }
    fun setUpAdapter(itemList : MutableList<com.example.messanger.model.Message>) {

        adapter = SingleRecycleAdapter(this,itemList) {
            val pop = PopupMenu(this,messageRV)
            pop.inflate(R.menu.context_menu)

            pop.setOnMenuItemClickListener { item ->

                when (item.itemId) {
                    R.id.item_delete_for_me -> {
                        database.child("messages").child(currentUid()!!).child(it!!.to).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (ob in dataSnapshot.children) {
                                    ob.getRef().removeValue()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Getting Post failed, log a message
                                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                                // ...
                            }
                        })
                    }

                    R.id.item_delete_for_all -> {
                        database.child("messages").child(it!!.to).child(currentUid()!!).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (ob in dataSnapshot.children) {
                                    ob.getRef().removeValue()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Getting Post failed, log a message
                                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                                // ...
                            }
                        })
                        database.child("messages").child(currentUid()!!).child(it!!.to).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (ob in dataSnapshot.children) {
                                    ob.getRef().removeValue()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Getting Post failed, log a message
                                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                                // ...
                            }
                        })
                    }
                }
                true
            }
            pop.show()
            true

        }
        messageRV.layoutManager = LinearLayoutManager(this)
        messageRV.setHasFixedSize(true)
        messageRV.adapter = adapter
    }
}