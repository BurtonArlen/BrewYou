package com.burton.arlen.brewyou.adapters;

import android.content.Context;

import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.util.ItemTouchHelperAdapter;
import com.burton.arlen.brewyou.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Guest on 6/14/17.
 */

public class FirebaseBadBeerListAdapter extends FirebaseRecyclerAdapter<Beer, FirebaseBadBeerViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseBadBeerListAdapter(Class<Beer> modelClass, int modelLayout,
                                      Class<FirebaseBadBeerViewHolder> viewHolderClass,
                                      Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void populateViewHolder(FirebaseBadBeerViewHolder viewHolder, Beer model, int position) {
        viewHolder.bindBeer(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
