package com.example.flicksmovieapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flicksmovieapp.models.Config;
import com.example.flicksmovieapp.models.Movie;


import org.parceler.Parcels;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.example.flicksmovieapp.R.layout.item_movie;

//Created by GLORIA

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //list of movies
    ArrayList<Movie> movies;
      //config
    Config config;
    //context
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
        //get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view
        View movieView = inflater.inflate(item_movie, parent, false);
        return new ViewHolder(movieView);
    }
    //binds an inflated view to a new item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        //determinate the current orientation
        boolean isPortrait =context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        //build url for image
        String imageUrl = null;

        //if in portrait mode,load the poster image
        if (isPortrait) {
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());

        }else {
            //load the backdrop image
            imageUrl= config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        }

        //get the correct placeholder and image view for the current orientation
        int placeholderId = isPortrait ?  R.drawable.flicks_movie_placeholderr :R.drawable.flicks_backdrop_placeholderr;
        ImageView imageView = isPortrait ?  holder.ivPosterImage: holder.ivBackdropImage;



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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //track
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
       // add this as the itemView's OnClickListener
       itemView.setOnClickListener(this);
   }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // show the activity
                context.startActivity(intent);
            }
        }

        }
    }

