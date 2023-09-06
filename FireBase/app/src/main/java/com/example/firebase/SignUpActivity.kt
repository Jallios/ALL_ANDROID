package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebase.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var SignUp_SU : Button

    lateinit var Email_SU : EditText
    lateinit var Password_SU : EditText

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var collection : CollectionReference

    lateinit var email : String
    lateinit var password : String

    lateinit var pref : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        pref = getSharedPreferences("1",MODE_PRIVATE)

        SignUp_SU = findViewById(R.id.SignUp_SU)

        Email_SU = findViewById(R.id.Email_SU)
        Password_SU = findViewById(R.id.Password_SU)

        firebaseAuth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        collection = db.collection("users")

        SignUp_SU.setOnClickListener {
            validData()

        }
    }
    fun validData(){

        email = Email_SU.getText().toString().trim()
        password = Password_SU.getText().toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email_SU.setError("Неправильно введена почта")
        }else if(TextUtils.isEmpty(password)){
            Password_SU.setError("Пароль не должен быть пустым")
        }else if(password.length < 6){
            Password_SU.setError("Пароль должен содержать более 6 символов")
        }else{
            fireBaseSignUp()
        }

    }
    fun fireBaseSignUp(){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val firebaseUser:FirebaseUser = firebaseAuth.currentUser!!
            val currentEmail:String = firebaseUser.email!!
            collection.add(User(firebaseUser.email!!, firebaseUser.uid))
                .addOnSuccessListener {
                    Toast.makeText(this, "Пользователь $currentEmail зарегистрированн", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                    Toast.makeText(this,"Ошибка", Toast.LENGTH_LONG).show()
                }
        }.addOnFailureListener{
            Toast.makeText(this,"Ошибка", Toast.LENGTH_LONG).show()
        }
        intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
    }

}