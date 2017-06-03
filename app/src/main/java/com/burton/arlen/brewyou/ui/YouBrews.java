package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.burton.arlen.brewyou.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YouBrews extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.returnFromYB) Button mReturnFromYB;
    @Bind(R.id.youBrewList) ListView mYouBrewList;
    @Bind(R.id.returnToSearchFromYB) Button mReturnToSearchFromYB;

    private ArrayList<String> goodBrews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_brews);
        ButterKnife.bind(this);
        mReturnFromYB.setOnClickListener(this);
        mReturnToSearchFromYB.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v == mReturnFromYB){
            Intent intent = new Intent(YouBrews.this, MainActivity.class);
            startActivity(intent);
        }
        if (v == mReturnToSearchFromYB){
            Intent intent = new Intent(YouBrews.this, BrewSearch.class);
            startActivity(intent);
        }
    }
}
