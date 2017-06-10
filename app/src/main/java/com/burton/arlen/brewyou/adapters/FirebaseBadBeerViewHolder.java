package com.burton.arlen.brewyou.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.ui.BeerDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by arlen on 6/9/17.
 */

public class FirebaseBadBeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseBadBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBeer(Beer beer) {
        ImageView iconView = (ImageView) mView.findViewById(R.id.iconView);
        TextView mName = (TextView) mView.findViewById(R.id.nameText);
        TextView mStyle = (TextView) mView.findViewById(R.id.styleText);
        TextView mAvailability = (TextView) mView.findViewById(R.id.availabilityText);
        ImageView thumbsUp = (ImageView) mView.findViewById(R.id.imageView4);

        Picasso.with(mContext)
                .load(beer.getImageLarge())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(iconView);

        mName.setText(beer.getName());
        mStyle.setText(beer.getStyle());
        mAvailability.setText(beer.getAvailability());
        thumbsUp.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Beer> beers = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_BEER)
                .child(uid)
                .child("hate");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    beers.add(snapshot.getValue(Beer.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BeerDetail.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("beers", Parcels.wrap(beers));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
