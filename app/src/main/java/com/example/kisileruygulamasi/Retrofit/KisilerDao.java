package com.example.kisileruygulamasi.Retrofit;

import com.example.kisileruygulamasi.entity.KisilerCevap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KisilerDao {
    //http://kasimadalan.pe.hu/kisiler/tum_kisiler.php
    @GET("kisiler/tum_kisiler.php")
    Call<KisilerCevap> tumKisiler();
}
