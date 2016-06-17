package com.example.ashiagrawal.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by ashiagrawal on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(movie.title);
        viewHolder.tvOverview.setText(movie.overview);
        String imageURL = "";
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageURL = movie.posterUrl;
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageURL = movie.backgroundUrl;
        }
        Picasso.with(getContext()).load(imageURL).placeholder(R.drawable.loading).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivPoster);
        return convertView;
    }
}
