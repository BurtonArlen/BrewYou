package com.burton.arlen.brewyou.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.models.Beer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerDetailFrag extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 230;
    @Bind(R.id.hateButton) Button mHateButton;
    @Bind(R.id.likeButton) Button mLikeButton;
    @Bind(R.id.learnMoreButton) Button mGoogleIt;
    @Bind(R.id.beerLabel) ImageView beerLabel;
    @Bind(R.id.nameText) TextView nameText;
    @Bind(R.id.descText) TextView description;
    @Bind(R.id.availabilityText) TextView availability;
    @Bind(R.id.styleText) TextView style;


    private Beer mBeer;

    public static BeerDetailFrag newInstance(Beer beer) {
        BeerDetailFrag beerDetailFragment = new BeerDetailFrag();
        Bundle args = new Bundle();
        args.putParcelable("beer", Parcels.wrap(beer));
        beerDetailFragment.setArguments(args);
        return beerDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeer = Parcels.unwrap(getArguments().getParcelable("beer"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_detail2, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mBeer.getImageLarge()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(beerLabel);

        nameText.setText(mBeer.getName());
        style.setText(mBeer.getStyle());
        availability.setText(mBeer.getAvailability());
        description.setText(mBeer.getDescripton());

        mHateButton.setOnClickListener(this);
        mLikeButton.setOnClickListener(this);
        mGoogleIt.setOnClickListener(this);

        if("hate" == mBeer.isOpinion()){
            mHateButton.setVisibility(View.GONE);
            mHateButton.setFocusable(false);
        }
        if("like" == mBeer.isOpinion()){
            mLikeButton.setVisibility(View.GONE);
            mLikeButton.setFocusable(false);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mLikeButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference beerRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEER).child(uid).child("like");
            DatabaseReference pushRef = beerRef.push();
            String pushId = pushRef.getKey();
            mBeer.setPushId(pushId);
            mBeer.likeBeer();
            pushRef.setValue(mBeer);


            Toast.makeText(getContext(), "Great Beer", Toast.LENGTH_SHORT).show();
        }
        if (v == mHateButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference beerRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEER).child(uid).child("hate");
            DatabaseReference pushRef = beerRef.push();
            String pushId = pushRef.getKey();
            mBeer.setPushId(pushId);
            mBeer.hateBeer();
            pushRef.setValue(mBeer);


            Toast.makeText(getContext(), "Beer Sucked", Toast.LENGTH_SHORT).show();
        }
        if (v == mGoogleIt){
            String term = mBeer.getGoogle() + " beer";
            Intent googleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(term));
            startActivity(googleIntent);
        }

    }
}