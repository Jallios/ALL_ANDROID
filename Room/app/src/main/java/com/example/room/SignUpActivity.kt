package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class SignUpActivity : AppCompatActivity() {

    lateinit var save_User : Button
    lateinit var FirstName_signUp : EditText
    lateinit var LastName_signUp : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val db = AppDatabase.getAppDataBase(applicationContext)

        save_User = findViewById(R.id.save_User)
        FirstName_signUp = findViewById(R.id.firstName_signUP)
        LastName_signUp = findViewById(R.id.lastName_signUp)

        save_User.setOnClickListener{



            val first_signUp = FirstName_signUp.text.toString()
            val last_signUp = LastName_signUp.text.toString()

            val userDao = db?.userDao()

            userDao!!.insertUser(User(null,first_signUp,last_signUp,(0.0)))

            intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }
    }
}