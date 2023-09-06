package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }
        public void start(View v)
        {


            EditText email=(EditText) findViewById(R.id.email);
            EditText pass=(EditText) findViewById(R.id.pass);
            EditText name=(EditText) findViewById(R.id.name);

            Intent intent=new Intent(this,NewActivity.class);
            // в ключ username пихаем текст из первого текстового поля
            intent.putExtra("namer", name.getText().toString());

            startActivity(intent);
        }
    }
