package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



import org.w3c.dom.Text;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        TextView welc = (TextView) findViewById(R.id.welc);
        Intent intent = getIntent();
        String name = intent.getStringExtra("namer");
        welc.setText("Добро пожаловать, "+name);
    }
}