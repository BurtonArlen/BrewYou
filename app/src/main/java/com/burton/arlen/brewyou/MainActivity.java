package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.aboutPageButton) Button mAboutPageButton;
    @Bind(R.id.youBrewPageButton) Button mYouBrewPageButton;
    @Bind(R.id.notYouBrewPageButton) Button mNotYouBrewPageButton;
//    @Bind(R.id.searchBeerButton) Button mSearchBeerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAboutPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutApp.class);
                startActivity(intent);
            }
        });
        mYouBrewPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YouBrews.class);
                startActivity(intent);
            }
        });
        mNotYouBrewPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotYouBrews.class);
                startActivity(intent);
            }
        });
    }
}
