package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.models.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AddActivity : AppCompatActivity() {
    lateinit var fab_add : FloatingActionButton

    lateinit var header_add : EditText
    lateinit var text_add : EditText

    lateinit var db : FirebaseFirestore
    lateinit var collection : CollectionReference

    lateinit var pref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        fab_add = findViewById(R.id.fab_add)
        header_add = findViewById(R.id.header_note_add)
        text_add = findViewById(R.id.text_note_add)

        pref = getSharedPreferences("1",MODE_PRIVATE)
        val user_id : String = pref.getString("uid","")!!
        db = FirebaseFirestore.getInstance()
        collection = db.collection("note")


        fab_add.setOnClickListener {
            AddNote(header_add.text.toString().trim(),text_add.text.toString().trim(),user_id)
        }
    }
    fun AddNote(header : String, text: String,user:String){
        collection.add(Notes(header,text,user,collection.document().id)).addOnSuccessListener {
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainMenuActivity::class.java);
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainMenuActivity::class.java);
            startActivity(intent)
        }
    }
}