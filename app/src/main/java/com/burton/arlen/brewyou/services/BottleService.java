package com.burton.arlen.brewyou.services;

import android.util.Log;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.models.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    public ArrayList<Beer> processResults(Response response){
        ArrayList<Beer> beers = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            Log.d("jsonData", jsonData);
            if (response.isSuccessful()){
                JSONObject beersJSON = new JSONObject(jsonData);
                Log.d("beersJSON", beersJSON.toString());
                JSONArray resultsJSON = beersJSON.getJSONArray("data");
                Log.d("resultsJSON", resultsJSON.toString());
                String lengthOresults = String.format("%d", resultsJSON.length());
                Log.d("lengthOresults", lengthOresults);
                for (int i = 0; i < resultsJSON.length(); i++){
                    String valueOi = String.format("%d", i);
                    Log.d("valueOi", valueOi);
                    JSONObject beerJSON = resultsJSON.getJSONObject(i);
                    if(!beerJSON.toString().contains("\"labels\"") || (!beerJSON.toString().contains("\"available\""))) {
                        Log.d("WEAKSAUCE", "WEAKSAUCE");
                     } else {
                        Log.d("beerJSON1", resultsJSON.getJSONObject(i).getJSONObject("labels").toString());
                        String name = beerJSON.getString("name");
                        String id = beerJSON.getString("id");
                        String imageIcon = beerJSON.getJSONObject("labels").optString("icon");
                        String imageMedium = beerJSON.getJSONObject("labels").optString("medium");
                        String imageLarge = beerJSON.getJSONObject("labels").optString("large");
                        String style = beerJSON.getJSONObject("style").optString("shortName");
                        String availability = beerJSON.getJSONObject("available").getString("name");;
                        Beer beer = new Beer(name, id, imageIcon, imageMedium, imageLarge, style, availability);
                        beers.add(beer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
