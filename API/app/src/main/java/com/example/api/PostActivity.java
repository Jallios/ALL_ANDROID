package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    EditText Car_name, Car_detail, Student_score, StudentFIO,Car_img;
    Button Car_add;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Car_name = findViewById(R.id.Car_name);
        Car_detail = findViewById(R.id.Car_detail);
        Student_score = findViewById(R.id.Student_score);
        StudentFIO = findViewById(R.id.StudentFIO);
        Car_img = findViewById(R.id.Car_img);

        Car_add = findViewById(R.id.Car_add);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);

        Car_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Car car = new Car(0,Car_name.getText().toString(), Car_detail.getText().toString(),Car_img.getText().toString(), StudentFIO.getText().toString(), Integer.parseInt(Student_score.getText().toString()));
                Call<Car> postCar = apiInterface.postCar(car);

                postCar.enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        if (response.isSuccessful())
                        {
                            finish();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Car> call, Throwable t) {

                    }
                });
            }
        });

    }
}