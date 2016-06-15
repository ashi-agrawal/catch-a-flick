package com.example.ashiagrawal.flixster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ashiagrawal on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        tvTitle.setText(movie.title);
        String imageUrl = "https://i.imgur.com/tGbaZCY.jpg";
        Picasso.with(getContext()).load(imageUrl).into(ivPoster);

        Log.d("MoviesAdapter", "Position: " + position);

        //ivPoster.set
        return convertView;
    }
}
