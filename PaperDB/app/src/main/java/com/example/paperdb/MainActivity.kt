package com.example.paperdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecycleAdapter
    private val carItemsList: MutableList<Car> = mutableListOf()
    lateinit var rv :RecyclerView
    lateinit var  buttonAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.carRV)
        buttonAdd = findViewById(R.id.buttonAdd)
        Paper.init(this)


        buttonAdd.setOnClickListener{
            intent = Intent(this, AddActivity::class.java);
            startActivity(intent)
        }

        setUpAdapter()
    }

    private fun setUpAdapter() {

        carItemsList.addAll(PaperDb.getCar())
        adapter = RecycleAdapter(this, carItemsList,
            {startActivity(Intent(this, UpdateAndDelete :: class.java).apply {
                putExtra("name", it?.name)
                putExtra("model", it?.model)
            })})

        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        rv.adapter = adapter
    }
}
