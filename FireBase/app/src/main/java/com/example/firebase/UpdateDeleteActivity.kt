package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class UpdateDeleteActivity : AppCompatActivity() {
    lateinit var fab_update : FloatingActionButton
    lateinit var fab_delete : FloatingActionButton

    lateinit var header_update : EditText
    lateinit var text_update : EditText

    lateinit var db : FirebaseFirestore
    lateinit var collection : CollectionReference

    lateinit var pref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        fab_update = findViewById(R.id.fab_update)
        fab_delete = findViewById(R.id.fab_delete)

        header_update = findViewById(R.id.header_note_update)
        text_update = findViewById(R.id.text_note_update)

        pref = getSharedPreferences("1",MODE_PRIVATE)
        val user_id : String = pref.getString("uid","")!!

        db = FirebaseFirestore.getInstance()
        collection = db.collection("note")

        val ID:String = intent.getStringExtra("ID").toString()
        val header:String = intent.getStringExtra("header").toString()
        val text:String = intent.getStringExtra("text").toString()

        header_update.setText(header)
        text_update.setText(text)

        fab_delete.setOnClickListener {
            collection.document(ID).delete().addOnSuccessListener {
                Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show()
                intent = Intent(this, MainMenuActivity::class.java);
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
            }
        }

        fab_update.setOnClickListener {
            collection.document(ID).update("header",header_update.text.toString(),"text",text_update.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show()
                intent = Intent(this, MainMenuActivity::class.java);
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
            }


        }
    }
}