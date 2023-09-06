package com.example.room

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserActivity : AppCompatActivity() {

    lateinit var save : Button
    lateinit var add : Button
    lateinit var out : Button
    lateinit var economy : Button
    lateinit var balance : Button

    lateinit var FirstName_user : EditText
    lateinit var LastName_user : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        save = findViewById(R.id.save)
        balance = findViewById(R.id.balance)
        economy = findViewById(R.id.economy)

        FirstName_user = findViewById(R.id.firstName_user)
        LastName_user = findViewById(R.id.lastName_user)

        val pref : SharedPreferences
        pref = getSharedPreferences("1",MODE_PRIVATE)

        val db = AppDatabase.getAppDataBase(applicationContext)
        val userDao = db?.userDao()


        val login = pref.getString("login","")
        val password =  pref.getString("password","")

        FirstName_user.setText(login)
        LastName_user.setText(password)


        save.setOnClickListener{

            if (login != "" && password != "") {
                val id = userDao!!.getUserName(login!!)
                userDao!!.updateUser(User(id.id, FirstName_user.text.toString(), LastName_user.text.toString(),id.balance))
                intent = Intent(this, MainActivity::class.java);
                startActivity(intent)
            }
        }
        economy.setOnClickListener{
            intent = Intent(this, EconomyActivity::class.java);
            startActivity(intent)
        }
        balance.setOnClickListener {
            intent = Intent(this, BalanceActivity::class.java);
            val id = userDao!!.getUserName(login!!)
            intent.putExtra("user",id.id)
            startActivity(intent)
        }

    }
}