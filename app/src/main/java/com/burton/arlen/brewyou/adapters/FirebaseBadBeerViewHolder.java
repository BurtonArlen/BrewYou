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
import com.burton.arlen.brewyou.util.ItemTouchHelperViewHolder;
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

public class FirebaseBadBeerViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView iconView;
    private View mView;
    private Context mContext;
//    private String rQual;
//    private int rQualInt;

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.5f)
                .scaleX(0.7f)
                .scaleY(0.7f)
                .rotation(1080f)
                .setDuration(150);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .rotation(0f)
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

    public FirebaseBadBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBeer(Beer beer) {
//        rQualInt = Integer.parseInt(beer.getIndex()) + 1;
//        rQual = "Rank: " + String.valueOf(rQualInt);
        iconView = (ImageView) mView.findViewById(R.id.iconView);
        TextView mName = (TextView) mView.findViewById(R.id.nameText);
        TextView mStyle = (TextView) mView.findViewById(R.id.styleText);
        TextView mAvailability = (TextView) mView.findViewById(R.id.availabilityText);
        ImageView thumbsUp = (ImageView) mView.findViewById(R.id.imageView4);
        TextView rank = (TextView) mView.findViewById(R.id.rankText);

        Picasso.with(mContext)
                .load(beer.getImageLarge())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(iconView);

        mName.setText(beer.getName());
        mStyle.setText(beer.getStyle());
        mAvailability.setText(beer.getAvailability());
//        rank.setText(rQual);
        thumbsUp.setVisibility(View.GONE);
    }
}

