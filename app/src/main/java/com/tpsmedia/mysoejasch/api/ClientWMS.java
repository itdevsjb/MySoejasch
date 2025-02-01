package com.tpsmedia.mysoejasch.api;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientWMS {

    public static final String BASE_URL_WMS = "http://192.168.0.206/api-soejasch-wms/";
    //public static final String BASE_URL_WMS = "http://114.129.18.62:1000/api-soejasch-wms/";

    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit = null;

    // Menambahkan parameter context pada metode untuk mendapatkan External Cache Directory
    public static Retrofit getClientWMS(Context context) {
        if (okHttpClient == null) {
            // Mendapatkan cache directory menggunakan context.getExternalCacheDir()
            File cacheDirectory = new File(context.getExternalCacheDir(), "http_cache");
            Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024); // 10 MB

            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .cache(cache)
                    .build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_WMS)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
