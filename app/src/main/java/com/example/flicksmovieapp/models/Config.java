package com.example.flicksmovieapp.models;


//Created By Gloria

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {

    String posterSize;
    String imageBaseUrl;
    String backdropSize;

    public Config (JSONObject object) throws JSONException {
        JSONObject images = object.getJSONObject("images");
        //get the image base url
        imageBaseUrl = images.getString("secure_base_url");
        //get the poster size
        JSONArray posterSizeOptions = images.getJSONArray("poster_sizes");
        //use the option at index 3 or w342 as a fallback
        posterSize = posterSizeOptions.optString(3, "w342");
        //parse  the backdrop size
        JSONArray backdropSizeOptions =images.getJSONArray("backdrop_sizes");
        backdropSize = backdropSizeOptions.optString(1, "w780");
    }

    //helper for the image url
    public String getImageUrl (String size, String path){
        return String.format("%s%s%s", imageBaseUrl, size, path); //concatenate all three

    }

    public String getPosterSize() {
        return posterSize;
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public String getBackdropSize() {
        return backdropSize;
    }
}
