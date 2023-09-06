package com.example.p50720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private Button buttonMainview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.text_view);

        Intent intent = getIntent();
        if(intent.hasExtra("message")) {
            // Извлекаем message
            String message = intent.getStringExtra("message");
            textView.setText(message);
        }
    }
}