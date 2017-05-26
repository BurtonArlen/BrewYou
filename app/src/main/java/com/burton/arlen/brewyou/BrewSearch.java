package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

        mReturnFromSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrewSearch.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
