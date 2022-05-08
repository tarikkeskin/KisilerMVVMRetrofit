package com.example.kisileruygulamasi.Retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/";

    public static KisilerDao getKisilerDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(KisilerDao.class);
    }
}
