package com.example.flicksmovieapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flicksmovieapp.models.Config;
import com.example.flicksmovieapp.models.Movie;


import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.example.flicksmovieapp.R.layout.item_movie;

//Created by GLORIA

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //list of movies
      ArrayList<Movie> movies;
      //config
    Config config;
    Context context;
      ViewGroup parent;
      ViewHolder holder;
       int position;


    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;

    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    //create inflates a new view
  @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(item_movie, parent, false);
        return new ViewHolder(movieView);
    }
 //binds an inflated view to a new item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        //determinate the current situation
        boolean isPortrait =context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        //build url for image
        String imageUrl = null;

        //if in portrait mode
        if (isPortrait) {
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());

        }else{
            //load the backdrop image
            imageUrl= config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        }

        //get the current image view
        int placeholderId = isPortrait ? R.drawable.flicks_backdrop_placeholderr : R.drawable.flicks_backdrop_placeholderr;
        ImageView imageView = isPortrait ? holder.ivPosterImage : holder.ivBackdropImage;



        //load image using Glide
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 25,0))
                .placeholder(placeholderId)
                .error(placeholderId)
                .into(imageView);
    }
    //returns the total number of item in the list
    @Override
    public int getItemCount() {
        return movies.size() ;
    }

    // create the view holder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {
             ImageView ivPosterImage;
             ImageView ivBackdropImage;
             TextView tvTitle;
             TextView tvOverview;



   public ViewHolder(View itemView) {
       super(itemView);
       ivPosterImage =(ImageView) itemView.findViewById(R.id.ivPosterImage);
       ivBackdropImage = (ImageView) itemView.findViewById(R.id.ivBackdropImage);
       tvOverview  =  (TextView) itemView.findViewById(R.id.tvOverview);
       tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
   }
    }
}
