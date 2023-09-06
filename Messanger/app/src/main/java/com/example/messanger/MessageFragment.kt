package com.example.messanger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
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
import com.google.firebase.installations.Utils


class MessageFragment : Fragment() {
    lateinit var recycleViewUsers: RecyclerView
    lateinit var editText: EditText
    lateinit var fab: FloatingActionButton
    lateinit var adapterFriends: RecycleAdapterFriends
    private val userItemsList : MutableList<User> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleViewUsers = view.findViewById(R.id.rv_message)
        editText = view.findViewById(R.id.search_ET_message)
        fab = view.findViewById(R.id.fab_search_message)


        database.child("NODE_FOLLOWING").child(currentUid()!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (it in dataSnapshot.children) {
                    Log.i("firebase", it.key.toString())
                    database.child("Users").addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (it2 in dataSnapshot.children)
                            {
                                if (it2.child("uid").value.toString() == it.key.toString()){
                                    userItemsList.add(User(it2.child("login").value.toString(), it2.child("surname").value.toString(),it2.child("name").value.toString(),it2.child("email").value.toString(), it2.child("uid").value.toString(),it2.child("image").value.toString()))
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)

    }
    fun setUpAdapter(item : MutableList<User>) {

        if (activity != null){
            adapterFriends = RecycleAdapterFriends(requireActivity().applicationContext,item) {
                unfollow(it!!.uid)

            }
            recycleViewUsers.layoutManager = LinearLayoutManager(context)
            recycleViewUsers.setHasFixedSize(true)
            recycleViewUsers.adapter = adapterFriends
        }

    }
    fun follow(uid: String) {
        setFollow(uid, true) {
            database.child("NODE_FOLLOWING").child(currentUid()!!).child(uid).child("CHILD_UID").setValue(uid)
            database.child("NODE_FOLLOWERS").child(uid).child(currentUid()!!).child("CHILD_UID").setValue(
                currentUid()!!)
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