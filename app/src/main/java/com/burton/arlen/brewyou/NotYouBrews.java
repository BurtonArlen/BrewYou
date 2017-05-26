package com.burton.arlen.brewyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotYouBrews extends AppCompatActivity {
    @Bind(R.id.returnFromNYB) Button mReturnFromNYB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_you_brews);

        ButterKnife.bind(this);

        mReturnFromNYB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotYouBrews.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
