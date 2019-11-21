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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class KontenWisataAct extends AppCompatActivity {

    LinearLayout btn_location;
    TextView lokasi,nama_curug,deskripsi;
    ImageView image;
    RatingBar mRating;
    DatabaseReference reference,wisataratedb,reference2,ratingshow;



    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konten_wisata);
        mRating = (RatingBar) findViewById(R.id.mRating);
        btn_location = findViewById(R.id.btn_location);
        lokasi = findViewById(R.id.lokasi);
        nama_curug = findViewById(R.id.nama_curug);
        deskripsi = findViewById(R.id.deskripsi);
        image = findViewById(R.id.image);

        getUsernameLocal();
        displayCustomerRelatedObjectd();

        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("nama_wisata");

        final String jenis_ticket_baru2 = bundle.getString("nama_wisata");
        String[] items1 = jenis_ticket_baru2.split(" ");
        String nama=items1[0];
        String curug=items1[1];

        Toast.makeText(getApplicationContext(),nama,Toast.LENGTH_SHORT).show();

        wisataratedb = FirebaseDatabase.getInstance().getReference().child("RatingWisata").child(jenis_ticket_baru);
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

        //Untuk tampil konten
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama).child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                nama_curug.setText(dataSnapshot.child("nama_curug").getValue().toString());
                deskripsi.setText(dataSnapshot.child("deskripsi").getValue().toString());
                Picasso.with(KontenWisataAct.this)
                        .load(dataSnapshot.child("url_photo1")
                                .getValue().toString()).centerCrop().fit()
                        .into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goweb = new Intent(android.content.Intent.ACTION_VIEW);
                goweb.setData(Uri.parse("https://www.google.co.id/maps/place/Curug+Ciherang/@-6.6367625,106.9962804,17z/data=!3m1!4b1!4m5!3m4!1s0x2e69b751d918d66f:0xeee07e3c098f5da!8m2!3d-6.6367678!4d106.9984691"));
                startActivity(goweb);
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
