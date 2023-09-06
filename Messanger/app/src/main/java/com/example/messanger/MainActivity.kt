package com.example.messanger

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.messanger.utils.auth
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var to_signUp: TextView
    lateinit var Btn_signIn: Button
    lateinit var pref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.ET_email_signIn)
        password = findViewById(R.id.ET_password_signIn)
        to_signUp = findViewById(R.id.TV_to_signUp)
        Btn_signIn = findViewById(R.id.Btn_sigIn)

        pref = getSharedPreferences("User",MODE_PRIVATE)

        if(pref.getString("email","").toString() != "" && pref.getString("password","").toString() != ""){
            intent = Intent(this, FragmentActivity::class.java);
            startActivity(intent)
        }

        to_signUp.setOnClickListener{
            intent = Intent(this, SignUp_Activity::class.java);
            startActivity(intent)
        }

        Btn_signIn.setOnClickListener {
            if (!TextUtils.isEmpty(email.text.toString().trim()) && !TextUtils.isEmpty(password.text.toString().trim())) {

                auth!!.signInWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            pref.edit().putString("email", email.text.toString().trim()).putString("password",password.text.toString().trim()).putString("uid",auth.currentUser!!.uid).commit()
                            intent = Intent(this, FragmentActivity::class.java);
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}