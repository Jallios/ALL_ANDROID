package com.example.messanger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messanger.model.User
import com.example.messanger.utils.currentUid
import com.example.messanger.utils.database
import com.google.android.gms.tasks.Tasks
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.math.log


class FriendsFragment : Fragment() {

    lateinit var recycleViewUsers: RecyclerView
    lateinit var editText: EditText
    lateinit var fab:FloatingActionButton
    lateinit var adapterUsers: RecycleAdapterUsers
    private val userItemsList : MutableList<User> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleViewUsers = view.findViewById(R.id.rv_friends)
        editText = view.findViewById(R.id.search_ET)
        fab = view.findViewById(R.id.fab_search)

        database.child("Users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (it in dataSnapshot.children) {
                    userItemsList.add(User(it.child("login").value.toString(), it.child("surname").value.toString(),it.child("name").value.toString(),it.child("email").value.toString(), it.child("uid").value.toString(),it.child("image").value.toString()))
                }
                Log.i("firebase", userItemsList.toString())
                setUpAdapter(userItemsList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

        fab.setOnClickListener {
            userItemsList.clear()
            database.child("Users").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (it in dataSnapshot.children) {
                        if (it.child("login").toString().contains(editText.text.toString().trim()))
                        {
                            userItemsList.add(User(it.child("login").value.toString(), it.child("surname").value.toString(),it.child("name").value.toString(),it.child("email").value.toString(), it.child("uid").value.toString(),it.child("image").value.toString()))
                        }
                    }
                    Log.i("firebase", userItemsList.toString())
                    setUpAdapter(userItemsList)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                    // ...
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)

    }
    fun setUpAdapter(item : MutableList<User>) {

        adapterUsers = RecycleAdapterUsers(requireContext(),item,{
            follow(it!!.uid)
        })
        recycleViewUsers.layoutManager = LinearLayoutManager(context)
        recycleViewUsers.setHasFixedSize(true)
        recycleViewUsers.adapter = adapterUsers
    }
    fun follow(uid: String) {
        setFollow(uid, true) {
            database.child("NODE_FOLLOWING").child(currentUid()!!).child(uid).child("CHILD_UID").setValue(uid)
            database.child("NODE_FOLLOWERS").child(uid).child(currentUid()!!).child("CHILD_UID").setValue(currentUid()!!)
        }
    }
    fun unfollow(uid: String) {
        setFollow(uid, false) {
            database.child("NODE_FOLLOWING").child(currentUid()!!).child(uid).child("CHILD_UID").removeValue()
            database.child("NODE_FOLLOWERS").child(uid).child(currentUid()!!).child("CHILD_UID").removeValue()
        }
    }
    fun setFollow(uid: String, follow: Boolean, onSuccess: () -> Unit) {
        val followsTask = database.child("NODE_USERS").child(currentUid()!!).child("following")
            .child(uid).setValue(follow)
        val followersTask = database.child("NODE_USERS").child(uid).child("followers")
            .child(currentUid()!!).setValue(follow)
        Tasks.whenAll(followsTask, followersTask).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess()
            } else {
            }
        }
    }
}