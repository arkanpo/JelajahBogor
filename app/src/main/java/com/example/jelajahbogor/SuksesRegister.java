package com.example.jelajahbogor;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuksesRegister extends AppCompatActivity {

    ImageView logoapp;
    TextView judul1,judul2;
    Button tombol;
    Animation btt,ttb,logosplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_register);

        logoapp = findViewById(R.id.logoapp);
        judul1 = findViewById(R.id.judul1);
        judul2 = findViewById(R.id.judul2);
        tombol = findViewById(R.id.tombol);

        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this,R.anim.ttb);
        logosplash = AnimationUtils.loadAnimation(this,R.anim.logosplash);

        logoapp.startAnimation(logosplash);
        judul1.startAnimation(btt);
        judul2.startAnimation(btt);
        tombol.startAnimation(btt);

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(SuksesRegister.this,HomeAct.class);
                startActivity(abc);
            }
        });


    }
}
