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
    @Bind(R.id.returnFromB) Button mReturnFromYB;
    @Bind(R.id.brewList) ListView mYouBrewList;

    private ArrayList<String> goodBrews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brew_lists);
        ButterKnife.bind(this);
        mReturnFromYB.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v == mReturnFromYB){
            Intent intent = new Intent(YouBrews.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
