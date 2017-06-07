package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_search);
        ButterKnife.bind(this);
        mReturnFromSearch.setOnClickListener(this);

        Intent intent = getIntent();
        String beerSearchTerm = intent.getStringExtra("beerSearchTerm");
        getBeer(beerSearchTerm);
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
}

