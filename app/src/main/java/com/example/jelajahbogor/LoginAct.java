package com.example.jelajahbogor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAct extends AppCompatActivity {

    TextView daftar;
    EditText xusername,xpassword;
    Button btn_login;

    DatabaseReference reference;

    String USERNAME_KEY = "username_key";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        daftar = findViewById(R.id.daftar);
        btn_login = findViewById(R.id.btn_login);
        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_login.setEnabled(false);
                btn_login.setText("Loading...");

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Kosong",Toast.LENGTH_SHORT).show();
                    btn_login.setEnabled(true);
                    btn_login.setText("SIGN IN");
                }
                else {
                    if (password.isEmpty()){
                        Toast.makeText(getApplicationContext(),"password Kosong",Toast.LENGTH_SHORT).show();
                        btn_login.setEnabled(true);
                        btn_login.setText("SIGN IN");
                    }
                    else{
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){

                                    //ambil data password dari database
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                    //validasi dengan password data base

                                    if (password.equals(passwordFromFirebase)){

                                        //penyimpanan lokal

                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        //pindah act

                                        Intent gohome = new Intent(LoginAct.this, HomeAct.class);
                                        startActivity(gohome);
                                    }

                                    else {
                                        Toast.makeText(getApplicationContext(),"password salah",Toast.LENGTH_SHORT).show();
                                        btn_login.setEnabled(true);
                                        btn_login.setText("SIGN IN");
                                    }

                                }

                                else{
                                    Toast.makeText(getApplicationContext(),"Username tidak ada",Toast.LENGTH_SHORT).show();
                                    btn_login.setEnabled(true);
                                    btn_login.setText("SIGN IN");
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }



            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ccd = new Intent(LoginAct.this, RegisterOneAct.class);
                startActivity(ccd);
            }
        });
    }
}
