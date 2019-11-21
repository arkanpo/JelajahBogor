package com.example.jelajahbogor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Profile> profiles;

    public MyAdapter(Context c, ArrayList<Profile> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview2,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nama.setText(profiles.get(i).getNama_curug());
        myViewHolder.lokasi.setText(profiles.get(i).getLokasi());
        Picasso.with(context).load(profiles.get(i).getUrl_photo1()).into(myViewHolder.image);

        final String getNamaWisata = profiles.get(i).getNama_curug();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godetailwisata = new Intent(context,konten_wisata2.class);
                godetailwisata.putExtra("nama_wisata", getNamaWisata);
                context.startActivity(godetailwisata);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,lokasi,txt_rating;
        ImageView image;
        RatingBar ratingview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.nama);
            lokasi = (TextView) itemView.findViewById(R.id.lokasi);
            ratingview = (RatingBar) itemView.findViewById(R.id.ratingview);
            lokasi = (TextView) itemView.findViewById(R.id.lokasi);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
