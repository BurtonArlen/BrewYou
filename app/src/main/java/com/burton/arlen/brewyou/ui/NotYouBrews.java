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
    @Bind(R.id.returnFromB) Button mReturnFromNYB;
    @Bind(R.id.brewList) ListView mNotYouBrewList;


    private ArrayList<String> badBrews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brew_lists);
        ButterKnife.bind(this);
        mReturnFromNYB.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        if (v == mReturnFromNYB){
            Intent intent = new Intent(NotYouBrews.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
