package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutApp extends AppCompatActivity {

    @Bind(R.id.returnFromAbout) Button mReturnFromAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        ButterKnife.bind(this);

        mReturnFromAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutApp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
