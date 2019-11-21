package com.example.jelajahbogor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class konten_wisata2 extends AppCompatActivity {

    TextView lokasi,nama_curug,deskripsi,txt_rating;
    RatingBar mRating,ratingview;
    ImageView image,image1,image2,image3;
    DatabaseReference reference,wisataratedb,reference2,ratingshow,wisataratedbview;
    FloatingActionButton floatingActionButton;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";

    int ratingsSum = 0;
    float ratingsTotal = 0;
    float ratingsAvg = 0;
    String maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konten_wisata2);
        mRating = (RatingBar) findViewById(R.id.mRating);
        ratingview = (RatingBar) findViewById(R.id.ratingview);
        lokasi = findViewById(R.id.lokasi);
        nama_curug = findViewById(R.id.nama_curug);
        deskripsi = findViewById(R.id.deskripsi);
        image = findViewById(R.id.image);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        txt_rating = findViewById(R.id.txt_rating);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        getUsernameLocal();
        displayCustomerRelatedObjectd();
        final Toolbar toolbar = findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("nama_wisata");
        final String jenis_ticket_baru2 = bundle.getString("nama_wisata");
        String[] items1 = jenis_ticket_baru2.split(" ");
        String nama=items1[0];
        String curug=items1[1];

        Toast.makeText(getApplicationContext(),nama + jenis_ticket_baru,Toast.LENGTH_SHORT).show();

        wisataratedb = FirebaseDatabase.getInstance().getReference().child("RatingWisata").child(jenis_ticket_baru);

        wisataratedbview = FirebaseDatabase.getInstance().getReference().child("RatingWisata");
        wisataratedbview.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.child(jenis_ticket_baru).getChildren()){
                    ratingsSum = ratingsSum + Integer.valueOf(child.getValue().toString());
                    ratingsTotal++;
                }
                if (ratingsTotal!=0){
                    ratingsAvg = ratingsSum/ratingsTotal;
                    ratingview.setRating(ratingsAvg);
                    txt_rating.setText(Float.toString(ratingsAvg));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //untuk insert reting
        reference2 = FirebaseDatabase.getInstance().getReference().child("RatingUsers").child(username_key_new).child(jenis_ticket_baru);
        //untuk tampil rating
        ratingshow = FirebaseDatabase.getInstance().getReference().child("RatingUsers").child(username_key_new).child(jenis_ticket_baru);
        ratingshow.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        if (child.getKey().equals("rating")){
                            mRating.setRating(Integer.valueOf(child.getValue().toString()));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama).child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maps = (dataSnapshot.child("maps").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                nama_curug.setText(dataSnapshot.child("nama_curug").getValue().toString());
                deskripsi.setText(dataSnapshot.child("deskripsi").getValue().toString());
                Picasso.with(konten_wisata2.this)
                        .load(dataSnapshot.child("url_photo1")
                                .getValue().toString()).centerCrop().fit()
                        .into(image);

                Picasso.with(konten_wisata2.this)
                        .load(dataSnapshot.child("url_photo1")
                                .getValue().toString()).centerCrop().fit()
                        .into(image1);

                Picasso.with(konten_wisata2.this)
                        .load(dataSnapshot.child("url_photo2")
                                .getValue().toString()).centerCrop().fit()
                        .into(image2);

                Picasso.with(konten_wisata2.this)
                        .load(dataSnapshot.child("url_photo3")
                                .getValue().toString()).centerCrop().fit()
                        .into(image3);


                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goweb = new Intent(android.content.Intent.ACTION_VIEW);
                        goweb.setData(Uri.parse(maps));
                        startActivity(goweb);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void displayCustomerRelatedObjectd(){
        mRating.setVisibility(View.VISIBLE);
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                wisataratedb.child(username_key_new).setValue(rating);
                reference2.child("rating").setValue(rating);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
