package com.example.kisileruygulamasi.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.kisileruygulamasi.Retrofit.ApiUtils;
import com.example.kisileruygulamasi.Retrofit.KisilerDao;
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
        Log.e("Kişi Kayıt",kisi_ad+" - "+kisi_tel);
    }

    public void kisiGuncelle(int kisi_id,String kisi_ad,String kisi_tel){
        Log.e("Kişi Güncelle",kisi_id+" - "+kisi_ad+" - "+kisi_tel);
    }

    public void kisiAra(String aramaKelimesi){
        Log.e("Kişi Ara",aramaKelimesi);
    }

    public void kisiSil(int kisi_id){
        Log.e("Kişi Sil",String.valueOf(kisi_id));
    }

    public void tumKisileriAl(){
        kisilerDao.tumKisiler().enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {
                List<Kisiler> liste = response.body().getKisiler();
                kisilerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {

            }
        });
    }
}
