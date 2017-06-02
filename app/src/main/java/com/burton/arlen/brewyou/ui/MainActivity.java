package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.burton.arlen.brewyou.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.aboutPageButton) Button mAboutPageButton;
    @Bind(R.id.youBrewPageButton) Button mYouBrewPageButton;
    @Bind(R.id.notYouBrewPageButton) Button mNotYouBrewPageButton;
    @Bind(R.id.searchBeerButton) Button mSearchBeerButton;
    @Bind(R.id.welcomeText) TextView mWelcomeText;
    @Bind(R.id.taglineText) TextView mTaglineText;
    @Bind(R.id.beerSearchEnter) EditText mBeerSearchEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        mWelcomeText.setTypeface(font);
        mTaglineText.setTypeface(font);
        mAboutPageButton.setOnClickListener(this);
        mNotYouBrewPageButton.setOnClickListener(this);
        mYouBrewPageButton.setOnClickListener(this);
        mSearchBeerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchBeerButton){
            String beerSearchTerm = mBeerSearchEnter.getText().toString();
            if (beerSearchTerm.trim().length() != 0){
                Intent intent = new Intent(MainActivity.this, BrewSearch.class);
                intent.putExtra("beerSearchTerm", beerSearchTerm);
                startActivity(intent);
            } else {
                mBeerSearchEnter.setError("You gotta look for something before you can find it");
                mBeerSearchEnter.setText("");
            }
        }
        if (v == mAboutPageButton){
            Intent intent = new Intent(MainActivity.this, AboutApp.class);
            startActivity(intent);
        }
        if(v == mYouBrewPageButton){
            Intent intent = new Intent(MainActivity.this, YouBrews.class);
            startActivity(intent);
        }
        if(v == mNotYouBrewPageButton){
            Intent intent = new Intent(MainActivity.this, NotYouBrews.class);
            startActivity(intent);
        }

    }
}
