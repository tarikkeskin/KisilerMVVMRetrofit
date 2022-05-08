package com.example.kisileruygulamasi.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.kisileruygulamasi.Retrofit.ApiUtils;
import com.example.kisileruygulamasi.Retrofit.KisilerDao;
import com.example.kisileruygulamasi.entity.CRUDCevap;
import com.example.kisileruygulamasi.entity.Kisiler;
import com.example.kisileruygulamasi.entity.KisilerCevap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KisilerDaoRepository {
    private MutableLiveData<List<Kisiler>> kisilerListesi;
    private KisilerDao kisilerDao;

    public KisilerDaoRepository() {
        kisilerDao = ApiUtils.getKisilerDaoInterface();
        kisilerListesi = new MutableLiveData();
    }

    public MutableLiveData<List<Kisiler>> kisileriGetir(){
        return kisilerListesi;
    }

    public void kisiKayit(String kisi_ad, String kisi_tel){
        kisilerDao.kisiEkle(kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                String mesaj = response.body().getMessage();
                Log.e("Response",mesaj);
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) { }
        });
    }

    public void kisiGuncelle(int kisi_id,String kisi_ad,String kisi_tel){
        kisilerDao.kisiGuncelle(kisi_id,kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) { }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) { }
        });
    }

    public void kisiAra(String aramaKelimesi){
        kisilerDao.kisiAra(aramaKelimesi).enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {
                List<Kisiler> liste = response.body().getKisiler();
                kisilerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) { }
        });
    }

    public void kisiSil(int kisi_id){
        kisilerDao.kisiSil(kisi_id).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                tumKisileriAl();
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) { }
        });
    }

    public void tumKisileriAl(){
        kisilerDao.tumKisiler().enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {
                List<Kisiler> liste = response.body().getKisiler();
                kisilerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) { }
        });
    }
}
