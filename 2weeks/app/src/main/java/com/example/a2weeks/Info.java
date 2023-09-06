package com.example.a2weeks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView text = findViewById(R.id.text_info);
        TextView ops = findViewById(R.id.info);
        ImageView image = findViewById(R.id.image_info);

        Cars car;
        car = getIntent().getParcelableExtra("item");
        if (car !=null){
            ops.setText(car.getModelCars());
            image.setBackgroundResource(car.getImgCars());
            text.setText(car.getOpisanie());
        }


    }
}