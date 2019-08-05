package com.example.flicksmovieapp.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.lang.reflect.Array;

@Parcel // annotation indicates class is Parcelable

public class Movie {

    //values from api
    String title;
    String overview;
    String posterPath;
    String backdropPath;//only the path
    String release_date;
    String original_language;
    Double voteAverage;
    //String popularity;


    public Movie() {
    }




    //initialize from API
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble ("vote_average");
        release_date = object.getString("release_date");
        original_language =object.getString("original_language");



    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {

        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }
}
