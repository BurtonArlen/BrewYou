package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.burton.arlen.brewyou.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Bind(R.id.youBrewPageButton) Button mYouBrewPageButton;
    @Bind(R.id.notYouBrewPageButton) Button mNotYouBrewPageButton;
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
        mNotYouBrewPageButton.setOnClickListener(this);
        mYouBrewPageButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    private void signOut() {
        Intent intent = new Intent(MainActivity.this, GoogleSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mAuth.signOut();
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_flow_menu_2, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            signOut();
            return true;
        }
        if (id == R.id.about_us){
            Intent intent = new Intent(MainActivity.this, AboutApp.class);
            startActivity(intent);
        }
        if (id == R.id.search_page){
            Intent intent = new Intent(MainActivity.this, BrewSearch.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
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
