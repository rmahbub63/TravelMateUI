package com.example.mahbub.travelmateui.adapter;

/**
 * Created by MAHBUB on 31-Oct-17.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.model.NewPlacesModel;
import com.example.mahbub.travelmateui.model.PopularPlacesModel;

import java.util.ArrayList;

public class NewPlaceListAdapter extends
        RecyclerView.Adapter<NewPlaceListAdapter.HorizontalViewHolderPopularPlace>{

    private Context context;
    private ArrayList<NewPlacesModel> newPlaceList;

    public NewPlaceListAdapter(Context context, ArrayList<NewPlacesModel> newPlaceList) {
        this.context = context;
        this.newPlaceList = newPlaceList;
    }

    @Override
    public HorizontalViewHolderPopularPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_place_list_item, null);
        HorizontalViewHolderPopularPlace viewHolder = new HorizontalViewHolderPopularPlace(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolderPopularPlace holder, final int position) {
        holder.placeImage.setImageResource(newPlaceList.get(position).getImageId());
        holder.placeName.setText(newPlaceList.get(position).getPlaceName());
        holder.placeRating.setRating(newPlaceList.get(position).getPlaceRating());
        holder.placeReview.setText(newPlaceList.get(position).getNoOfPlaceReview());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowSelectedPlaceActivity.class);
                intent.putExtra("place_name", newPlaceList.get(position).getPlaceName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newPlaceList.size();
    }

    public class HorizontalViewHolderPopularPlace extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView placeImage;
        TextView placeName;
        RatingBar placeRating;
        TextView placeReview;

        public HorizontalViewHolderPopularPlace(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            placeImage = itemView.findViewById(R.id.full_image);
            placeName = itemView.findViewById(R.id.name_text_view);
            placeRating = itemView.findViewById(R.id.ratingBar);
            placeReview = itemView.findViewById(R.id.review_text_view);
        }
    }
}
