package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class twin extends AppCompatActivity {

    private Button btnstart2, btnstop2, back;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twin);

        btnstart2 = findViewById(R.id.start2);
        btnstop2 = findViewById(R.id.stop2);
        imageView2 = findViewById(R.id.image2);
        back = findViewById(R.id.back);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        Animation animation_start = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animRotateIn_btnstart = AnimationUtils.loadAnimation(this, R.anim.top_out);
        Animation animRotateIn_btnstop = AnimationUtils.loadAnimation(this, R.anim.bottom_in);

        btnstart2.startAnimation(animRotateIn_btnstart);
        btnstop2.startAnimation(animRotateIn_btnstop);
        imageView2.startAnimation(animation_start);


        btnstart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView2.startAnimation(animation);
            }
        });

        btnstop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView2.clearAnimation();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(twin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}