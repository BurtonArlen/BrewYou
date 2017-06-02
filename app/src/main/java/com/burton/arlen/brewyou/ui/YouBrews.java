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

public class YouBrews extends AppCompatActivity {
    @Bind(R.id.returnFromYB) Button mReturnFromYB;
    @Bind(R.id.youBrewList) ListView mYouBrewList;
    @Bind(R.id.returnToSearchFromYB) Button mReturnToSearchFromYB;

    private ArrayList<String> goodBrews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_brews);

        ButterKnife.bind(this);

        Intent intent = getIntent();


      if (intent != null) {
            String likedBeer = intent.getStringExtra("likedBeer");
            goodBrews.add(likedBeer);

       } else {
          ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, goodBrews);
          mYouBrewList.setAdapter(adapter);
      }
        mReturnFromYB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YouBrews.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mReturnToSearchFromYB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YouBrews.this, BrewSearch.class);
                startActivity(intent);
            }
        });
    }
}
