package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var SignIn : Button
    lateinit var SignUp : Button

    lateinit var Email:EditText
    lateinit var Password :EditText

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var user: FirebaseUser

    lateinit var pref : SharedPreferences

    lateinit var email: String
    lateinit var password :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignIn = findViewById(R.id.SignIn)
        SignUp = findViewById(R.id.SignUp)

        Email = findViewById(R.id.Email)
        Password = findViewById(R.id.Password)

        firebaseAuth = FirebaseAuth.getInstance()

        pref = getSharedPreferences("1",MODE_PRIVATE)

        if(pref.getString("email","").toString() != "" && pref.getString("password","").toString() != ""){
            intent = Intent(this, MainMenuActivity::class.java);
            startActivity(intent)
        }

        SignUp.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java);
            startActivity(intent)
        }

        SignIn.setOnClickListener {
            loginUser()
        }

    }
    private fun loginUser() {

        email = Email.text.toString().trim()
        password = Password.text.toString().trim()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            firebaseAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        pref.edit().putString("email", email).putString("password",password).putString("uid",firebaseAuth.currentUser!!.uid).commit()
                        intent = Intent(this, MainMenuActivity::class.java);
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }
}