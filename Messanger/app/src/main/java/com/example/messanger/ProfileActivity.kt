package com.example.messanger

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.messanger.model.User
import com.example.messanger.utils.auth
import com.example.messanger.utils.currentUid
import com.example.messanger.utils.database
import com.example.messanger.utils.storage
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.io.IOException
import java.util.*

class ProfileActivity : AppCompatActivity() {

    lateinit var surname: EditText
    lateinit var name: EditText
    lateinit var Btn_save: Button
    lateinit var userImage: ImageView
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        surname = findViewById(R.id.ET_surname_profile)
        name = findViewById(R.id.ET_name_profile)
        Btn_save = findViewById(R.id.Btn_save)
        userImage = findViewById(R.id.User_Image_profile)

        database.child("Users").child(currentUid()!!).get().addOnSuccessListener {
            for (nodeSnapshot in it.children) {
                surname.setText(it.child("surname").value.toString())
                name.setText(it.child("name").value.toString())
                Picasso.get().load(it.child("image").value.toString()).into(userImage);
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        userImage.setOnClickListener {
            launchGallery()
        }

        Btn_save.setOnClickListener {
                database.child("Users").child(currentUid()!!).get().addOnSuccessListener {
                    for (nodeSnapshot in it.children) {
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
                                        database.child("Users").child(currentUid()!!).setValue(User(it.child("login").value.toString(),surname.text.toString().trim(), name.text.toString().trim(),it.child("email").value.toString(), currentUid()!!,downloadUri.toString()))
                                    }

                                }
                        }else{
                            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
                intent = Intent(this, FragmentActivity::class.java);
                startActivity(intent)
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