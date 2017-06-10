package com.burton.arlen.brewyou.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.ui.BeerDetailFrag;

import java.util.ArrayList;

/**
 * Created by arlen on 6/9/17.
 */

public class BeerPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Beer> mBeers;

    public BeerPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<Beer> beers) {
        super(fm);
        mBeers = beers;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return BeerDetailFrag.newInstance(mBeers.get(position));
    }

    @Override
    public int getCount() {
        return mBeers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeers.get(position).getName();
    }
}
