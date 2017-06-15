package com.burton.arlen.brewyou.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burton.arlen.brewyou.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;
    private int mOrientation;
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;



    @Bind(R.id.youBrewPageButton) Button mYouBrewPageButton;
    @Bind(R.id.notYouBrewPageButton) Button mNotYouBrewPageButton;
    @Bind(R.id.imageLogin) ImageView mProfilePic;
    @Bind(R.id.anim1) ImageView youBrewAnim;
    @Bind(R.id.anim2) ImageView nYouBrewAnim;
    @Bind(R.id.mainSearchButton) Button mMainSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrientation = getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_land);
        } else {
            setContentView(R.layout.activity_main);
        }

        Context mContext = getApplicationContext();

        ButterKnife.bind(this);

        mMainSearchButton.setOnClickListener(this);
        mNotYouBrewPageButton.setOnClickListener(this);
        mYouBrewPageButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        String userName = mAuth.getCurrentUser().getDisplayName();
        Uri profilePic = mAuth.getCurrentUser().getPhotoUrl();
        setTitle("BrewYou: " + userName + "'s Profile");

        Picasso.with(mContext).load(profilePic)
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mProfilePic);
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
            youBrewAnim.animate()
                    .scaleX(1000f)
                    .scaleY(1000f)
                    .setDuration(10);
            mMainSearchButton.animate().alpha(0f);
            mProfilePic.animate().alpha(0f);
            mNotYouBrewPageButton.animate().alpha(0f);
            Intent intent = new Intent(MainActivity.this, YouBrews.class);
            startActivity(intent);
        }
        if(v == mNotYouBrewPageButton){
            nYouBrewAnim.animate()
                    .scaleX(1000f)
                    .scaleY(1000f)
                    .setDuration(10);
            mMainSearchButton.animate().alpha(0f);
            mProfilePic.animate().alpha(0f);
            mYouBrewPageButton.animate().alpha(0f);
            Intent intent = new Intent(MainActivity.this, NotYouBrews.class);
            startActivity(intent);
        }
        if (v == mMainSearchButton){
            mMainSearchButton.animate().alpha(0f);
            mProfilePic.animate().alpha(0f);
            mYouBrewPageButton.animate().alpha(0f);
            mNotYouBrewPageButton.animate().alpha(0f);
            Intent intent = new Intent(MainActivity.this, BrewSearch.class);
            startActivity(intent);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        youBrewAnim.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1000);
        nYouBrewAnim.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1000);
        mNotYouBrewPageButton.animate()
                .alpha(1f)
                .setDuration(1000);
        mMainSearchButton.animate()
                .alpha(1f)
                .setDuration(1000);
        mProfilePic.animate()
                .alpha(1f)
                .setDuration(1000);
        mYouBrewPageButton.animate()
                .alpha(1f)
                .setDuration(1000);
    }
}
