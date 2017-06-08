package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.adapters.SearchedBeerListAdapter;
import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.services.BottleService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BrewSearch extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.returnFromSearch) Button mReturnFromSearch;
    @Bind(R.id.searchBrewList) RecyclerView mSearchBrewList;

    private SharedPreferences mSharedPreferences;
    private String mRecentSearches;
    private SharedPreferences.Editor mEditor;
    public ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_search);
        ButterKnife.bind(this);
        mReturnFromSearch.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearches = mSharedPreferences.getString(Constants.PREFERENCES_NAME, null);

        if (mRecentSearches != null){
            getBeer(mRecentSearches);
        }

        Intent intent = getIntent();
        String beerSearchTerm = intent.getStringExtra("beerSearchTerm");
        getBeer(beerSearchTerm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    addToSharedPreferences(query);
                    getBeer(query);
                } else {
                    Toast.makeText(BrewSearch.this, "Please Enter Search Terms", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v == mReturnFromSearch) {
            Intent intent = new Intent(BrewSearch.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void getBeer(String name) {
        final BottleService bottleService = new BottleService();
        bottleService.findBeer(name, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBeers = bottleService.processResults(response);
                BrewSearch.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] beerNames = new String[mBeers.size()];
                        for (int i = 0; i < beerNames.length; i++) {
                            beerNames[i] = mBeers.get(i).getmName();
                        }
                        SearchedBeerListAdapter adapter = new SearchedBeerListAdapter(getApplicationContext(), mBeers);
                        mSearchBrewList.setAdapter(adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BrewSearch.this);
                        mSearchBrewList.setLayoutManager(layoutManager);
                        mSearchBrewList.setHasFixedSize(true);
                    }
                });
            }
        });
    }
    private void addToSharedPreferences(String name) {
        mEditor.putString(Constants.PREFERENCES_NAME, name).apply();
    }
}

