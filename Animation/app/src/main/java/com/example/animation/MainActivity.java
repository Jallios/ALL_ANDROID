package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button twin, frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.frame);
        twin = findViewById(R.id.twin);


        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(MainActivity.this, Frame.class);
                startActivity(Intent);
                overridePendingTransition(R.anim.perehod_frame, R.anim.back_frame);

            }
        });

        twin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, twin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.perehod_twin, R.anim.back_twin);
            }
        });
    }
}