package com.example.tchl.liaomei;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by happen on 2016/6/3.
 */
public class DrakeetRetrofit {
    final GankApi gankService;
    //final DrakeetApi drakeetService;

    // @formatter:off
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    DrakeetRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(12, TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .setEndpoint("http://gank.io/api")
                .setConverter(new GsonConverter(gson));
        RestAdapter gankRestAdapter = builder.build();
        gankService = gankRestAdapter.create(GankApi.class);
    }

    public GankApi getGankService() {
        return gankService;
    }
}
