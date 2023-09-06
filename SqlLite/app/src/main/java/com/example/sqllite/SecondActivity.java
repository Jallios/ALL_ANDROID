package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    EditText modelCar, opisanie;
    Button addCar, deleteCar, updateCar;
    String m, o;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        modelCar = findViewById(R.id.model);
        opisanie = findViewById(R.id.opisanie);
        addCar = findViewById(R.id.add_car);
        deleteCar = findViewById(R.id.delete_car);
        updateCar = findViewById(R.id.update_car);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);



        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cars cars = new Cars(0, modelCar.getText().toString(),
                        opisanie.getText().toString());

                dataBaseHelper.addCar(cars);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        m = getIntent().getStringExtra("model");
        o = getIntent().getStringExtra("opisanie");
        id = getIntent().getIntExtra("ID",0);

        modelCar.setText(m);
        opisanie.setText(o);

        updateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cars cars = new Cars(id, modelCar.getText().toString(),
                        opisanie.getText().toString());
                dataBaseHelper.updateCar(cars);

                // displaying a toast message that our course has been updated.
                Toast.makeText(SecondActivity.this, "Car Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cars cars = new Cars(id, modelCar.getText().toString(),
                        opisanie.getText().toString());
                dataBaseHelper.deleteCar(cars);

                // displaying a toast message that our course has been updated.
                Toast.makeText(SecondActivity.this, "Car Deleted..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}