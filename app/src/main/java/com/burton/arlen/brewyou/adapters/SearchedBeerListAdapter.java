package com.burton.arlen.brewyou.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.burton.arlen.brewyou.R;
import com.burton.arlen.brewyou.models.Beer;
import com.burton.arlen.brewyou.ui.BeerDetail;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arlen on 6/2/17.
 */

public class SearchedBeerListAdapter extends RecyclerView.Adapter<SearchedBeerListAdapter.BeerViewHolder>{
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    private ArrayList<Beer> mBeers = new ArrayList<>();
    private Context mContext;
    public SearchedBeerListAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mBeers = beers;
    }

    @Override
    public SearchedBeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_item, parent, false);
        BeerViewHolder viewHolder = new BeerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchedBeerListAdapter.BeerViewHolder holder, int position){
        holder.bindBeer(mBeers.get(position));
    }

    @Override
    public int getItemCount(){
        return mBeers.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.nameText) TextView nameText;
        @Bind(R.id.styleText) TextView styleText;
        @Bind(R.id.availabilityText) TextView availabilityText;
        @Bind(R.id.iconView) ImageView iconView;
        @Bind(R.id.imageView4) ImageView thumbsUp;
        @Bind(R.id.imageView3) ImageView thumbsDown;

        private Context mContext;
        public BeerViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BeerDetail.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("beers", Parcels.wrap(mBeers));
            mContext.startActivity(intent);
        }

        public void bindBeer(Beer beer){
            thumbsUp.setVisibility(View.GONE);
            thumbsDown.setVisibility(View.GONE);

            nameText.setText(beer.getName());
            styleText.setText(beer.getStyle());
            availabilityText.setText(beer.getAvailability());

            Picasso.with(mContext).load(beer.getImageMedium())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(iconView);
        }
    }
}
