package com.example.ashiagrawal.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ashiagrawal on 6/15/16.
 */
public class Movie {
    private static final String MOVIE_DATABASE_URL = "https://api.themoviedb.org/3";
    private static final String API_KEY ="?api_key=a5af19062d969af0a86c4baefb329769";
    public String title;
    public String posterUrl;
    public String backgroundUrl;
    public String release;
    public String overview;
    public float rating;

    public Movie(String title, String posterUrl, int rating, String overview, String backgroundUrl, String release) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.overview = overview;
        this.backgroundUrl = backgroundUrl;
        this.release = release;
    }



    public static ArrayList<Movie> fromJsonList(JSONObject response){
        JSONArray movieList = new JSONArray();
        try {
            movieList = response.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<Movie> returnList = new ArrayList<Movie>();
        JSONObject movieJSON;
        for (int i = 0; i < movieList.length(); i ++) {
            try {
                movieJSON = movieList.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Movie movie = Movie.fromJson(movieJSON);
            if (movie != null) {
                returnList.add(movie);
            }
        }
        return returnList;
    }

    private static Movie fromJson(JSONObject movieJSON){
        String title;
        String posterURL;
        String overview;
        String backgroundURL;
        String release;
        int rating;
        try {
            title = movieJSON.getString("title");
            overview = movieJSON.getString("overview");
            posterURL = movieJSON.getString("poster_path");
            posterURL = getPosterURL(posterURL);
            rating = movieJSON.getInt("vote_average");
            backgroundURL = movieJSON.getString("backdrop_path");
            backgroundURL = getPosterURL(backgroundURL);
            release = movieJSON.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        Movie newMovie = new Movie (title, posterURL, rating, overview, backgroundURL, release);
        return newMovie;
    }

    private static String getPosterURL(String append){
        return "https://image.tmdb.org/t/p/w342" + append;
    }

    public static String getAbsoluteURL(String relativeURL){
        return MOVIE_DATABASE_URL + relativeURL + API_KEY;
    }

    @Override
    public String toString() {
        return title + " - " + rating;
    }
}
