package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class BalanceActivity : AppCompatActivity() {

    lateinit var add_balance : Button
    lateinit var out_balance : Button
    lateinit var count_add_out : EditText
    lateinit var count : TextView
    lateinit var nal: RadioButton
    lateinit var nenal: RadioButton
    lateinit var radio: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        add_balance = findViewById(R.id.add_balance)
        out_balance = findViewById(R.id.out_balace)
        count_add_out = findViewById(R.id.count_add_out)
        count = findViewById(R.id.count)
        nal = findViewById(R.id.nal)
        nenal = findViewById(R.id.nal)
        radio = findViewById(R.id.radio)

        val userInfo = intent.getIntExtra("user",0)

        val db = AppDatabase.getAppDataBase(applicationContext)
        val countDao = db?.countDao()
        val userDao = db?.userDao()
        val info = userDao!!.getUserID(userInfo)
        count.setText(userDao!!.getUserID(userInfo).balance.toString())

        add_balance.setOnClickListener {

            val value = count_add_out.text.toString().toDouble()

            countDao!!.insertCount(Count(null,radio.checkedRadioButtonId,1,value,userInfo))

            userDao!!.updateUser(User(info.id, info.firstName, info.lastName,
                info.balance?.plus(value)
            ))

            count.setText(userDao!!.getUserID(userInfo).balance.toString())

            intent = Intent(this, UserActivity::class.java);
            startActivity(intent)
        }

        out_balance.setOnClickListener {

            val value = count_add_out.text.toString().toDouble()

            countDao!!.insertCount(Count(null,radio.checkedRadioButtonId,2,value,userInfo))

            userDao!!.updateUser(User(info.id, info.firstName, info.lastName,
                info.balance?.minus(value)
            ))
            count.setText(userDao!!.getUserID(userInfo).balance.toString())

            intent = Intent(this, UserActivity::class.java);
            startActivity(intent)
        }

    }
}