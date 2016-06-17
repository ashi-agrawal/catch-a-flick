package com.example.ashiagrawal.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> currentMovies = new ArrayList<Movie>();
    MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        adapter = new MoviesAdapter(this, currentMovies);
        TextView tvType = (TextView) findViewById(R.id.tvType);
        tvType.setText("Now Playing");
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }
        lvMovies.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Movie movie = (Movie) parent.getAdapter().getItem(position);
                Intent details = new Intent(MoviesActivity.this, DetailActivity.class);
                details.putExtra("name", movie.title);
                details.putExtra("release", movie.release);
                details.putExtra("overview", movie.overview);
                details.putExtra("rating", movie.rating);
                details.putExtra("image", movie.backgroundUrl);
                startActivity(details);
            }
        });
        this.getMovies("Now Playing");
    }

    private void getMovies(String type) {
        AsyncHttpClient client = new AsyncHttpClient();
        JsonHttpResponseHandler translate = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    currentMovies.addAll(Movie.fromJsonList(response));
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.d("onfailure", "this has failed");
            }
        };
        if (type == "Now Playing") {client.get(Movie.getAbsoluteURL("/movie/now_playing"), null, translate);}
        if (type == "Popular") {client.get(Movie.getAbsoluteURL("/movie/popular"), null, translate);}
    }
}
