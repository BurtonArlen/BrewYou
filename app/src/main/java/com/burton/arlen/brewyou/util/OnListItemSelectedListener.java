package com.burton.arlen.brewyou.util;

import com.burton.arlen.brewyou.models.Beer;

import java.util.ArrayList;

/**
 * Created by Guest on 6/14/17.
 */

public interface OnListItemSelectedListener {
    void onListItemSelected(Integer position, ArrayList<Beer> beers, String source);
}
