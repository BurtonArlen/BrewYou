package com.burton.arlen.brewyou.services;

import com.burton.arlen.brewyou.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by arlen on 6/2/17.
 */

public class BottleService {
    public static void findBeer(String beerName, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.ENDPOINT_QUERY, beerName)
                .addQueryParameter(Constants.ENDPOINT_KEYWORD, Constants.ENDPOINT_TYPE)
                .addQueryParameter(Constants.KEY_PARAM, Constants.BEER_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
