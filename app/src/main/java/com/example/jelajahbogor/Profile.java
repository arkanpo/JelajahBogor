package com.example.jelajahbogor;

public class Profile {
   String nama_curug,lokasi,url_photo1;

    public Profile() {
    }

    public Profile(String nama_curug, String lokasi, String url_photo1) {
        this.nama_curug = nama_curug;
        this.lokasi = lokasi;
        this.url_photo1 = url_photo1;
    }

    public String getNama_curug() {
        return nama_curug;
    }

    public void setNama_curug(String nama_curug) {
        this.nama_curug = nama_curug;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getUrl_photo1() {
        return url_photo1;
    }

    public void setUrl_photo1(String url_photo1) {
        this.url_photo1 = url_photo1;
    }
}
