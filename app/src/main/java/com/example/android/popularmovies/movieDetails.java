package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class movieDetails extends AppCompatActivity {

    TextView titleTextView ;
    TextView releaseDateTextView;
    TextView ratingTextView;
    TextView descriptionTextView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String imageUrl = intent.getStringExtra("imageUrl");
        String releaseDate = intent.getStringExtra("releaseDate");
        String rating = intent.getStringExtra("rating");
        String description = intent.getStringExtra("description");

        titleTextView = findViewById(R.id.title_text_view);
        titleTextView.setText(title);
        getSupportActionBar().setTitle(R.string.movie_details);

        releaseDateTextView = findViewById(R.id.release_date);
        releaseDateTextView.setText(releaseDate);

        ratingTextView = findViewById(R.id.rating);
        ratingTextView.setText(rating);

        descriptionTextView = findViewById(R.id.description);
        descriptionTextView.setText(description);

        imageView = findViewById(R.id.movie_poster);
        Picasso.with(this).load(imageUrl).into(imageView);
    }
}