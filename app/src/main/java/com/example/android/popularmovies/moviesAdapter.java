package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class moviesAdapter extends ArrayAdapter<movies> {

    public String title;
    public String releaseDate;
    public String rating;
    public String description;
    public String imageUrl;

    ImageView movieImage;

    final Context mContext = this.getContext();

    public moviesAdapter(Activity context, ArrayList<movies> movies) {
        super(context, 0 , movies);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View gridItemView = convertView;
        if (gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview , parent , false);
        }

        movies currentMovie = getItem(position);

        title = currentMovie.getTitle();

        movieImage = (ImageView) gridItemView.findViewById(R.id.movie_image);
        imageUrl = currentMovie.getImagePath();
        Picasso.with(mContext).load(imageUrl).into(movieImage);

        releaseDate = currentMovie.getReleaseDate();
        rating = currentMovie.getRating();
        description = currentMovie.getDescription();

        movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies currentMovie = getItem(position);
                Intent intent = new Intent(mContext , movieDetails.class);
                intent.putExtra("title" , currentMovie.getTitle());
                intent.putExtra("imageUrl" , currentMovie.getImagePath());
                intent.putExtra("releaseDate" , currentMovie.getReleaseDate());
                intent.putExtra("rating" , currentMovie.getRating());
                intent.putExtra("description" , currentMovie.getDescription());
                mContext.startActivity(intent);
            }
        });

        return gridItemView;

    }

}
