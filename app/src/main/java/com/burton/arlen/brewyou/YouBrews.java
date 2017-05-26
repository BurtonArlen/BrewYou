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

public class YouBrews extends AppCompatActivity {
    @Bind(R.id.returnFromYB) Button mReturnFromYB;
    @Bind(R.id.youBrewList) ListView mYouBrewList;

    private ArrayList<String> goodBrews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_brews);

        ButterKnife.bind(this);

        mReturnFromYB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YouBrews.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
