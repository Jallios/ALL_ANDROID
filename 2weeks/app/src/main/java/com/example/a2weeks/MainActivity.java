package com.example.a2weeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycle_view);

        ArrayList<Cars> listCars = new ArrayList<Cars>();


        listCars.add(new Cars("Toyota Mark II", R.drawable.mark2,"Дешёвая быстрая машина, все столбы твои"));
        listCars.add(new Cars("Nissan Skyline GT-R (R34)", R.drawable.nissan, "Мечта любого школьника неподзревающего что это nissan"));
        listCars.add(new Cars("Toyota Supra A80", R.drawable.supra, "Тут я думаю всё ясно"));
        listCars.add(new Cars("Mitsubishi Lancer X", R.drawable.lancer , "RIP Lancer Evolution"));
        listCars.add(new Cars("Subaru WRX STI", R.drawable.subaru , "Дикий ценник за среднюю машину"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecycleAdapter(this, listCars));



    }
}