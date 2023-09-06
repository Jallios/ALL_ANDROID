package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.models.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.*
import java.io.Serializable


class MainMenuActivity : AppCompatActivity() {

    lateinit var Email_TV : TextView
    lateinit var Password_TV : TextView
    lateinit var UID_TV : TextView
    lateinit var fab: FloatingActionButton
    lateinit var noteRv: RecyclerView
    lateinit var adapter : RecycleAdapter
    private val noteItemsList : MutableList<Notes> = mutableListOf()
    lateinit var db : FirebaseFirestore
    lateinit var collection : CollectionReference

    lateinit var pref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        pref = getSharedPreferences("1",MODE_PRIVATE)
        Email_TV = findViewById(R.id.Email_TV)
        Password_TV = findViewById(R.id.Password_TV)
        UID_TV = findViewById(R.id.UID_TV)
        fab = findViewById(R.id.fab)
        noteRv = findViewById(R.id.noteRV)
        db = FirebaseFirestore.getInstance()

        collection = db.collection("note")



        Email_TV.setText((pref.getString("email","").toString()))
        Password_TV.setText((pref.getString("password","").toString()))
        UID_TV.setText(pref.getString("uid","").toString())

        fab.setOnClickListener{
            intent = Intent(this, AddActivity::class.java);
            startActivity(intent)
        }
        collection.whereEqualTo("user",pref.getString("uid","")).get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    noteItemsList.add(Notes(document.get("header").toString(),document.get("text").toString(),document.get("user").toString(),document.id))
                    setUpAdapter(noteItemsList)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
            }
    }

    fun setUpAdapter(item : MutableList<Notes>) {

        adapter = RecycleAdapter(this,item ,

            {startActivity(Intent(this, UpdateDeleteActivity :: class.java).apply {
               putExtra("ID",it?.ID)
                putExtra("header",it?.header)
                putExtra("text",it?.text)
            })})

        noteRv.layoutManager = LinearLayoutManager(this)
        noteRv.setHasFixedSize(true)
        noteRv.adapter = adapter
    }


}