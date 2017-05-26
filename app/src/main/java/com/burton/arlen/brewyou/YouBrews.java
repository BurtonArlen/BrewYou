package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YouBrews extends AppCompatActivity {
    @Bind(R.id.returnFromYB) Button mReturnFromYB;

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
