package com.example.jelajahbogor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeAct extends AppCompatActivity {

    Button btn_login,btn_signup;
    Animation btt,ttb;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_login = findViewById(R.id.btn_login);
        textView = findViewById(R.id.textView);
        btn_signup = findViewById(R.id.btn_signup);
        btt = AnimationUtils.loadAnimation(this,R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this,R.anim.ttb);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(WelcomeAct.this, RegisterOneAct.class);
                startActivity(abc);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(WelcomeAct.this,LoginAct.class);
                startActivity(abc);
            }
        });

        btn_signup.startAnimation(btt);
        btn_login.startAnimation(btt);
        textView.startAnimation(ttb);

    }
}
