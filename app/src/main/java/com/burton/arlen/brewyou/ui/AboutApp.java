package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.burton.arlen.brewyou.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutApp extends AppCompatActivity implements View.OnClickListener{

    private int mOrientation;
    @Bind(R.id.returnFromAbout) Button mReturnFromAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrientation = getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_about_app_land);
        } else {
            setContentView(R.layout.activity_about_app);
        }
        ButterKnife.bind(this);
        mReturnFromAbout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v == mReturnFromAbout){
            Intent intent = new Intent(AboutApp.this, GoogleSignInActivity.class);
            startActivity(intent);
        }
    }
}
