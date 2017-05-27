package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrewSearch extends AppCompatActivity {
    @Bind(R.id.returnFromSearch) Button mReturnFromSearch;
    @Bind(R.id.searchBrewList) ListView mSearchBrewList;

    private String[] searchedBrewsTemp = new String[] {"Beer1", "Beer2", "Beer3", "Beer4", "Beer5", "Beer6", "Beer7", "Beer8", "Beer9", "Beer10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_search);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searchedBrewsTemp);
        mSearchBrewList.setAdapter(adapter);

        mSearchBrewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String likedBeer = ((TextView)view).getText().toString();
                Toast.makeText(BrewSearch.this, likedBeer  + " has been added to your liked brews", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BrewSearch.this, YouBrews.class);
                intent.putExtra("likedBeer", likedBeer);
                startActivity(intent);

            }
        });

        mSearchBrewList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dislikedBeer = ((TextView)view).getText().toString();
                Toast.makeText(BrewSearch.this, dislikedBeer + " has been added to your disliked brews and will no longer appear in your searches", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BrewSearch.this, NotYouBrews.class);
                intent.putExtra("dislikedBeer", dislikedBeer);
                startActivity(intent);
                return true;
            }
        });

        mReturnFromSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrewSearch.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
