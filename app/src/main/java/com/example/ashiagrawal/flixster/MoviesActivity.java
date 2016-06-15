package com.example.ashiagrawal.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ArrayList<Movie> movies = Movie.getFakeMovies();
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        MoviesAdapter adapter = new MoviesAdapter(this, movies);
//        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }
    }
}
