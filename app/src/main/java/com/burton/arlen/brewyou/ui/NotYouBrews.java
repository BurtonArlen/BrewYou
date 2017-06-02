package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.burton.arlen.brewyou.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotYouBrews extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.returnFromNYB) Button mReturnFromNYB;
    @Bind(R.id.notYouBrewList) ListView mNotYouBrewList;
    @Bind(R.id.returnToSearchFromNYB) Button mReturnToSearchFromNYB;

    private ArrayList<String> badBrews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_you_brews);
        ButterKnife.bind(this);
        mReturnFromNYB.setOnClickListener(this);
        mReturnToSearchFromNYB.setOnClickListener(this);

        Intent intent = getIntent();

        if (intent != null) {
            String dislikedBeer = intent.getStringExtra("dislikedBeer");
            badBrews.add(dislikedBeer);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, badBrews);
            mNotYouBrewList.setAdapter(adapter);
        } else {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, badBrews);
            mNotYouBrewList.setAdapter(adapter);
        }

    }
    @Override
    public void onClick(View v){
        if (v == mReturnFromNYB){
            Intent intent = new Intent(NotYouBrews.this, MainActivity.class);
            startActivity(intent);
        }
        if (v == mReturnToSearchFromNYB){
            Intent intent = new Intent(NotYouBrews.this, BrewSearch.class);
            startActivity(intent);
        }
    }
}
