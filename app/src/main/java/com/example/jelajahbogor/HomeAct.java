package com.example.jelajahbogor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    ImageView photo_home_user;
    TextView nama_lengkap;
    LinearLayout btn_curug,btn_see,btn_mall,btn_cafe,btn_kuliner,btn_taman;

    DatabaseReference reference;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getUsernameLocal();

        photo_home_user = findViewById(R.id.photo_home_user);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        btn_curug = findViewById(R.id.btn_curug);
        btn_see = findViewById(R.id.btn_see);
        btn_mall = findViewById(R.id.btn_mall);
        btn_cafe = findViewById(R.id.btn_cafe);
        btn_kuliner = findViewById(R.id.btn_kuliner);
        btn_taman = findViewById(R.id.btn_taman);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                Picasso.with(HomeAct.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        photo_home_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,MyProfile.class);
                startActivity(abc);
            }
        });

        btn_curug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Curug");
                startActivity(abc);

            }
        });

        btn_mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Mall");
                startActivity(abc);

            }
        });

        btn_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Gunung");
                startActivity(abc);

            }
        });

        btn_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Cafe");
                startActivity(abc);

            }
        });

        btn_kuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Kuliner");
                startActivity(abc);
            }
        });

        btn_taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(HomeAct.this,ListWisataAct.class);
                abc.putExtra("jenis_ticket","Taman");
                startActivity(abc);
            }
        });


    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }

}
