package com.example.messanger

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.messanger.model.User
import com.example.messanger.utils.auth
import com.example.messanger.utils.currentUid
import com.example.messanger.utils.database
import com.example.messanger.utils.storage
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.IOException
import java.util.*

class SignUp_Activity : AppCompatActivity() {

    lateinit var surname: EditText
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var Btn_signUp: Button
    lateinit var userImage: ImageView
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        surname = findViewById(R.id.ET_surname_signUp)
        name = findViewById(R.id.ET_name_signUp)
        email = findViewById(R.id.ET_email_signUp)
        login = findViewById(R.id.ET_login_signUp)
        password = findViewById(R.id.ET_password_signUp)
        Btn_signUp = findViewById(R.id.Btn_sigUp)
        userImage = findViewById(R.id.User_Image)

        userImage.setOnClickListener {
            launchGallery()
        }

        Btn_signUp.setOnClickListener {


            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches()) {
                email.setError("Неправильно введена почта")
            } else if (TextUtils.isEmpty(password.text.toString().trim())) {
                password.setError("Пароль не должен быть пустым")
            } else if (password.text.toString().trim().length < 6) {
                password.setError("Пароль должен содержать более 6 символов")
            } else {
                auth.createUserWithEmailAndPassword(
                    email.text.toString().trim(),
                    password.text.toString().trim()
                ).addOnSuccessListener {
                    if(filePath != null){
                        val ref = storage?.child("userImage/" + UUID.randomUUID().toString())
                        val uploadTask = ref?.putFile(filePath!!)
                        val urlTask =
                            uploadTask!!.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                                if (!task.isSuccessful) {
                                    task.exception?.let {
                                        throw it
                                    }
                                }
                                return@Continuation ref.downloadUrl
                            }).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val downloadUri = task.result
                                    val user = mkUser(
                                        login.text.toString().trim(),
                                        surname.text.toString().trim(),
                                        name.text.toString().trim(),
                                        email.text.toString().trim(),
                                        currentUid()!!,downloadUri.toString())
                                    database.child("Users").child(currentUid()!!).setValue(user)

                                }

                            }
                    }else{
                        Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
                }
                intent = Intent(this, MainActivity::class.java);
                startActivity(intent)
            }

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                userImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun mkUser(
        login: String,
        surname: String,
        Name: String,
        Email: String,
        uid: String,
        image: String
    ): User {
        return User(login, surname, Name, Email, uid, image)
    }
    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

}