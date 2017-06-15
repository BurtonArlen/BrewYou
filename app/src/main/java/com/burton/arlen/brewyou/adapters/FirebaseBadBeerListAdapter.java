package com.burton.arlen.brewyou.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.ui.BeerDetail;
import com.burton.arlen.brewyou.util.ItemTouchHelperAdapter;
import com.burton.arlen.brewyou.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 6/14/17.
 */

public class FirebaseBadBeerListAdapter extends FirebaseRecyclerAdapter<Beer, FirebaseBadBeerViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Beer> mBeers = new ArrayList<>();

    private void setIndexInFirebase(){
        for (Beer beer : mBeers){
            int index = mBeers.indexOf(beer);
            DatabaseReference ref = getRef(index);
            beer.setIndex(Integer.toString(index));
            ref.setValue(beer);
        }
    }

    public FirebaseBadBeerListAdapter(Class<Beer> modelClass, int modelLayout,
                                   Class<FirebaseBadBeerViewHolder> viewHolderClass,
                                   Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBeers.add(dataSnapshot.getValue(Beer.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void populateViewHolder(final FirebaseBadBeerViewHolder viewHolder, Beer model, int position) {
        viewHolder.bindBeer(model);
        viewHolder.iconView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, BeerDetail.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("beers", Parcels.wrap(mBeers));

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBeers, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mBeers.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}