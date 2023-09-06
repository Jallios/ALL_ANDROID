package com.example.room

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {


    lateinit var signIn : Button
    lateinit var signUp : Button
    lateinit var FirstName : EditText
    lateinit var LastName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn = findViewById(R.id.signIn)
        signUp = findViewById(R.id.signUp)
        FirstName = findViewById(R.id.firstName)
        LastName = findViewById(R.id.lastName)

        val db = AppDatabase.getAppDataBase(applicationContext)
        val userDao = db?.userDao()
        val users: List<User> = userDao!!.getAll()


        val pref : SharedPreferences
        pref = getSharedPreferences("1",MODE_PRIVATE)

        if(pref.getString("login","").toString() != "" && pref.getString("password","").toString() != ""){
            intent = Intent(this, UserActivity::class.java);
            startActivity(intent)
        }

        signUp.setOnClickListener{

            intent = Intent(this, SignUpActivity::class.java);
            startActivity(intent)
        }

        signIn.setOnClickListener{

            val first = FirstName.text.toString()
            val last = LastName.text.toString()

            for(i in users)
            {
                if (first == i.firstName)
                {
                    if (last == i.lastName){
                        pref.edit().putString("login", first).putString("password",last).commit()
                        intent = Intent(this, UserActivity::class.java);
                        startActivity(intent)
                    }
                }
            }
        }

    }
}
