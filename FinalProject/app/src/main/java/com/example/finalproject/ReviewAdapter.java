package com.example.finalproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.Review;
import com.example.finalproject.ReviewList;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ReviewAdapter extends RealmRecyclerViewAdapter<Review, ReviewAdapter.MyViewHolder>  {
    ReviewList context;

    public ReviewAdapter(ReviewList context, @Nullable RealmResults<Review> data)
    {
        super(data, true); // autoupdate true
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create row view / viewHolder
        // only a limited number will be created
        View view = context.getLayoutInflater().inflate(R.layout.activity_review_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        // copy from the result object to the viewHolder for display
        Review review = getItem(position);

        // fill in the ViewHolder
        // non-strings need to be converted to String via String.valueOf()
        holder.reviews.setText(review.getReview());


        holder.delete.setTag(review);

        File getImageDir = context.getExternalCacheDir();
        File savedImage = new File(getImageDir, review.getImage());


        Picasso.with(context)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.photo);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reviews;
        ImageView photo;
        Button delete;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            reviews = itemView.findViewById(R.id.reviewRow);
            photo = itemView.findViewById(R.id.reviewImageRow);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(deleteListener);
        }
    }
    private View.OnClickListener deleteListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
//            Tag is attaching arbitrary object to an existing object
            Review reviews = (Review) v.getTag();
//            System.out.println(u);
            context.delete(reviews);
        }
    };
}
