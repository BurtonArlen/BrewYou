package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.burton.arlen.brewyou.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YouBrews extends AppCompatActivity{
    @Bind(R.id.brewList) ListView mYouBrewList;
    FirebaseAuth mAuth;

    private ArrayList<String> goodBrews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brew_lists);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_flow_menu_3, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void signOut() {
        Intent intent = new Intent(YouBrews.this, GoogleSignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mAuth.signOut();
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            signOut();
            return true;
        }
        if (id == R.id.user_profile) {
            Intent intent = new Intent(YouBrews.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_page2) {
            Intent intent = new Intent(YouBrews.this, BrewSearch.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.about_us) {
            Intent intent = new Intent(YouBrews.this, AboutApp.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}