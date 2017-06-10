package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.adapters.FirebaseBeerViewHolder;
import com.burton.arlen.brewyou.models.Beer;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YouBrews extends AppCompatActivity{
    private ArrayList<String> goodBrews = new ArrayList<>();
    FirebaseAuth mAuth;

    private DatabaseReference mBeerReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brew_lists);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mBeerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEER).child(uid).child("like");
        setUpFirebaseAdapter();
        mAuth = FirebaseAuth.getInstance();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Beer, FirebaseBeerViewHolder>
                (Beer.class, R.layout.beer_list_item, FirebaseBeerViewHolder.class,
                        mBeerReference) {

            @Override
            protected void populateViewHolder(FirebaseBeerViewHolder viewHolder,
                                              Beer model, int position) {
                viewHolder.bindBeer(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
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
