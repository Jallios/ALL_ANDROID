package com.example.paperdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.paperdb.Paper

class UpdateAndDelete : AppCompatActivity() {
    lateinit var  buttonDelete : Button
    lateinit var  buttonUpdate : Button
    lateinit var  name_UpDel : EditText
    lateinit var  model_Updel : EditText
    private val carItemsList: MutableList<Car> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        name_UpDel = findViewById(R.id.name_UpDel)
        model_Updel = findViewById(R.id.model_UpDel)

        val nameE:String = intent.getStringExtra("name").toString()
        val modelE:String = intent.getStringExtra("model").toString()

        name_UpDel.setText(nameE)
        model_Updel.setText(modelE)




        buttonUpdate.setOnClickListener{

            carItemsList.addAll(PaperDb.getCar())

            for (i in 0 until carItemsList.size) {
                if (carItemsList[i].name == nameE && carItemsList[i].model == modelE) {
                    carItemsList[i].name = name_UpDel.text.toString()
                    carItemsList[i].model = model_Updel.text.toString()

                    PaperDb.saveCar(carItemsList)
                }
            }

            Toast.makeText(this, nameE + " added to database", Toast.LENGTH_LONG).show()

            intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }

        buttonDelete.setOnClickListener{



            PaperDb.removeItem(Car(nameE, modelE))

            Toast.makeText(this, nameE + " delete from database", Toast.LENGTH_LONG).show()

            intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }

    }
}