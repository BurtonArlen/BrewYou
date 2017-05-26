package com.burton.arlen.brewyou;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.aboutPageButton) Button mAboutPageButton;
    @Bind(R.id.youBrewPageButton) Button mYouBrewPageButton;
    @Bind(R.id.notYouBrewPageButton) Button mNotYouBrewPageButton;
    @Bind(R.id.searchBeerButton) Button mSearchBeerButton;
    @Bind(R.id.welcomeText) TextView mWelcomeText;
    @Bind(R.id.taglineText) TextView mTaglineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        mWelcomeText.setTypeface(font);
        mTaglineText.setTypeface(font);

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
        mSearchBeerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrewSearch.class);
                startActivity(intent);
            }
        });
    }
}
