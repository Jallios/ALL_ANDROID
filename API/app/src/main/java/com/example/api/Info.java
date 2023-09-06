package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Info extends AppCompatActivity {

    ImageView imageView;
    TextView name, detail, studentFIO, score;
    ApiInterface apiInterface;
    Integer id;
    Button DeleteCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.img_car_info);
        name = findViewById(R.id.car_name_info);
        detail = findViewById(R.id.car_detail_info);
        studentFIO = findViewById(R.id.studentFIO_info);
        score = findViewById(R.id.student_score_info);
        DeleteCar = findViewById(R.id.Delete_car);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        id = getIntent().getIntExtra("item",0);
        Call<Car> getCar = apiInterface.getCarById(id);

        getCar.enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if(response.isSuccessful()) {
                    Car car = response.body();

                    Picasso.with(getApplicationContext()).load(car.getCar_Img()).into(imageView);
                    name.setText(car.getCar_Name());
                    detail.setText(car.getCar_Detail());
                    studentFIO.setText(car.getStudent_FIO());
                    score.setText(Integer.toString(car.getStudent_Score()));

                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();}
            }
            @Override
            public void onFailure(Call<Car> call, Throwable t) {

            }
        });

        DeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Car> deleteCar = apiInterface.deleteCar(id);
                deleteCar.enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        if(response.isSuccessful()) {
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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