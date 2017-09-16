package com.rangon.en_mmgeologydictionary.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rangon.en_mmgeologydictionary.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win on 9/10/17.
 */
public class RestClient {

    private final static String API_URL = BuildConfig.APP_GATEWAY;

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit != null) {
            return retrofit;
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static <T> T getService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
