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

public class Frame extends AppCompatActivity {

    private Button btnstart, btnstop, back2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        btnstart = findViewById(R.id.start);
        btnstop = findViewById(R.id.stop);
        imageView = findViewById(R.id.image);
        back2 = findViewById(R.id.back2);

        Animation animRotateIn_icon = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation animRotateIn_btnstart = AnimationUtils.loadAnimation(this, R.anim.left_out);
        Animation animRotateIn_btnstop = AnimationUtils.loadAnimation(this, R.anim.right_in);

        btnstart.startAnimation(animRotateIn_btnstart);
        btnstop.startAnimation(animRotateIn_btnstop);
        imageView.startAnimation(animRotateIn_icon);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.stop();
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(Frame.this, MainActivity.class);
                startActivity(Intent);
                overridePendingTransition(R.anim.scaleon, R.anim.scaleoff);

            }
        });

    }
}