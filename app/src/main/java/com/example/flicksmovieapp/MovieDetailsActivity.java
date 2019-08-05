package com.example.flicksmovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flicksmovieapp.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {
    // the movie to display
    Movie movie;

    // the view object
    TextView tvTitle;
    TextView tvOverview;
    TextView release_date;
    TextView original_language;
    RatingBar rbVoteAverage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // resolve the view object
        tvTitle =(TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        release_date = (TextView) findViewById(R.id.rdview);
        original_language = (TextView) findViewById(R.id.olview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview, release date and original language
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        original_language.setText(movie.getOriginal_language());
        release_date.setText(movie.getRelease_date());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float VoteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(VoteAverage = VoteAverage> 0 ? VoteAverage / 2.0f : VoteAverage);

    }
}
