package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView carsRecycle;
    ApiInterface apiInterface;
    Button go_to_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carsRecycle = findViewById(R.id.recycle_view);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);

        go_to_add = findViewById(R.id.go_to_add);

        go_to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PostActivity.class);
                startActivity(intent);
            }
        });

        Call<ArrayList<Car>> getCarList = apiInterface.getCarList();

        getCarList.enqueue(new Callback<ArrayList<Car>>() {
            @Override
            public void onResponse(Call<ArrayList<Car>> call, Response<ArrayList<Car>> response) {
                if(response.isSuccessful()){
                    carsRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    carsRecycle.setHasFixedSize(true);
                    ArrayList<Car> cars = response.body();
                    RecycleAdapter adapter = new RecycleAdapter(getApplicationContext(),cars);
                    carsRecycle.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Car>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}