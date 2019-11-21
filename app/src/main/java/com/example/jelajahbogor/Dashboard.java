package com.example.jelajahbogor;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class Dashboard extends AppCompatActivity {
    TextView lihat_semua;
    ImageView photo_home_user;
    Dialog myDialog;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.cafe, R.drawable.taman, R.drawable.mall, R.drawable.list1, R.drawable.gunung};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myDialog = new Dialog(this);
        carouselView = findViewById(R.id.carousel);
        lihat_semua = findViewById(R.id.lihat_semua);
        photo_home_user = findViewById(R.id.photo_home_user);

        lihat_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(Dashboard.this,kategori_wisata.class);
                startActivity(abc);
            }
        });

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });
    }

    public void ShowPopUp(View v){
        ImageView close;
        myDialog.setContentView(R.layout.activity_pop_up_profile);
        close =(ImageView) myDialog.findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        }); myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

}
