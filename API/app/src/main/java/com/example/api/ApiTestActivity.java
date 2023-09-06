package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiTestActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        tv = findViewById(R.id.tvTest);

        Call<ArrayList<UserRole>> userRoleCall = apiInterface.getRoles();
        userRoleCall.enqueue(new Callback<ArrayList<UserRole>>() {
            @Override
            public void onResponse(Call<ArrayList<UserRole>> call, Response<ArrayList<UserRole>> response) {
                if (response.isSuccessful()){
                   if (response.body()!= null){
                       for(UserRole item : response.body()){
                           tv.append(item.getRole() + "\n");
                       }
                   }
                }else{
                    Toast.makeText(ApiTestActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("err1", response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserRole>> call, Throwable t) {
                Toast.makeText(ApiTestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("err2", t.getMessage());
            }
        });


    }
}