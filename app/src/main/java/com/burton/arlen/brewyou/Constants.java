package com.burton.arlen.brewyou;

/**
 * Created by arlen on 6/2/17.
 */

public class Constants {
    public static final String ClientId = BuildConfig.ClientId;
    public  static final String BEER_KEY = BuildConfig.BEERKEY;
    public  static  final String BASE_URL= "http://api.brewerydb.com/v2/search/";
    public static final String KEY_PARAM = "key";
    public static final String ENDPOINT_QUERY = "q";
    public static final String ENDPOINT_KEYWORD = "type";
    public static final String ENDPOINT_TYPE = "beer";
    public static final String PREFERENCES_NAME = "name";
    public static final String FIREBASE_CHILD_SEARCH_NAME = "searchedBeer";
    public static final String FIREBASE_CHILD_BEER = "beers";



}
//http://api.brewerydb.com/v2/search/?q=Rainer&type=beer&key=57dcc32caa34e7ea24a812532cb63716
