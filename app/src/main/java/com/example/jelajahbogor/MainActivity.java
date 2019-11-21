package com.example.jelajahbogor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView applogo;
    TextView text_logo;
    Animation logosplash,textsplash;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUsernameLocal();
        applogo = findViewById(R.id.applogo);
        text_logo = findViewById(R.id.text_logo);
        logosplash = AnimationUtils.loadAnimation(this,R.anim.logosplash);
        textsplash = AnimationUtils.loadAnimation(this,R.anim.textsplash);

        applogo.startAnimation(logosplash);
        text_logo.startAnimation(textsplash);

    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent abc = new Intent(MainActivity.this, WelcomeAct.class);
                    startActivity(abc);
                    finish();

                }
            },2000);
        }else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent gohome = new Intent(MainActivity.this, HomeAct.class);
                    startActivity(gohome);
                    finish();

                }
            },2000);
        }
    }

}
