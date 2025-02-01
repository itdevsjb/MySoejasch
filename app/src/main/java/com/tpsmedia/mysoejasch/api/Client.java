package com.tpsmedia.mysoejasch.api;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static final String BASE_URL = "http://192.168.0.206/api-portal-soejasch/public/api/";
    public static final String BASE_URL_WMS = "http://192.168.0.206/api-soejasch-wms/";

    static OkHttpClient okHttpClient = null;
    static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        if (okHttpClient == null) {
            File cacheDirectory = new File(context.getExternalCacheDir(), "http_cache");
            Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .cache(cache)
                    .build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientWMS(Context context) {
        if (okHttpClient == null) {
            File cacheDirectory = new File(context.getExternalCacheDir(), "http_cache");
            Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

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
