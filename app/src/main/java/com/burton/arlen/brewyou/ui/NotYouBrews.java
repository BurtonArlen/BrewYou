package com.burton.arlen.brewyou.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.adapters.FirebaseBadBeerListAdapter;
import com.burton.arlen.brewyou.adapters.FirebaseBadBeerViewHolder;
import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.util.OnStartDragListener;
import com.burton.arlen.brewyou.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotYouBrews extends AppCompatActivity implements OnStartDragListener{
    private DatabaseReference mBeerReference;
    private FirebaseBadBeerListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brew_lists);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_BEER)
                .child(uid).child(Constants.FIREBASE_CHILD_OPINION_BAD)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mFirebaseAdapter = new FirebaseBadBeerListAdapter
                    (Beer.class, R.layout.beer_list_item_drag_land, FirebaseBadBeerViewHolder.class, query, this, this);
        } else {
            mFirebaseAdapter = new FirebaseBadBeerListAdapter
                    (Beer.class, R.layout.beer_list_item_drag, FirebaseBadBeerViewHolder.class, query, this, this);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
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
        Intent intent = new Intent(NotYouBrews.this, GoogleSignInActivity.class);
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
            Intent intent = new Intent(NotYouBrews.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_page2) {
            Intent intent = new Intent(NotYouBrews.this, BrewSearch.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.about_us) {
            Intent intent = new Intent(NotYouBrews.this, AboutApp.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
