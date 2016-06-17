package com.example.ashiagrawal.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getStringExtra("name");
        String release = getIntent().getStringExtra("release");
        String overview = getIntent().getStringExtra("overview");
        String image = getIntent().getStringExtra("image");
        float rating = getIntent().getFloatExtra("rating", -1);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(name);
        TextView tvRelease = (TextView) findViewById(R.id.tvRelease);
        tvRelease.setText("Release Date: " + release);
        TextView tvOverview = (TextView) findViewById(R.id.tvSummary);
        tvOverview.setText(overview);
        ImageView ivPicture = (ImageView) findViewById(R.id.ivBackground);
        Picasso.with(this.getApplicationContext()).load(image).placeholder(R.drawable.loading).transform(new RoundedCornersTransformation(10, 10)).into(ivPicture);
        TextView tvRating = (TextView) findViewById(R.id.tvRating);
        RatingBar ratings = (RatingBar) findViewById(R.id.ratingBar);
        if (rating != -1){
            tvRating.setText("Current Rating: " + (rating/2) + " out of 10.");
            ratings.setRating(rating/2);
        } else{
            tvRating.setText("You're the first to rate it!");
            ratings.setRating(0);
        }
    }
}
