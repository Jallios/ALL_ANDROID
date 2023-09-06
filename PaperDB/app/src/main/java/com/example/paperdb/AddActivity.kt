package com.example.paperdb

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.paperdb.Paper
import java.util.jar.Attributes



class AddActivity : AppCompatActivity() {
    lateinit var add : Button
    lateinit var enterName : EditText
    lateinit var enterModel : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        Paper.init(this)

        add = findViewById(R.id.buttonAdd)
        enterName = findViewById(R.id.name_add)
        enterModel = findViewById(R.id.model_add)


        add.setOnClickListener{



            val name = enterName.text.toString()
            val model = enterModel.text.toString()

            PaperDb.addItem(Car(name, model))

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }
}