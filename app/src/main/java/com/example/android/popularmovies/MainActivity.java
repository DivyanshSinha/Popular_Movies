package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<movies>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int MOVIE_LOADER_ID = 1;

    String API_KEY = ""; //<INSERT API KEY HERE>

    /** URL for popular movie data*/
    String REQUEST_URL_POPULAR_MOVIES = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

    /** URL for top-rated movie data*/
    String REQUEST_URL_TOP_RATED_MOVIES = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY;

    String REQUEST_URL = REQUEST_URL_POPULAR_MOVIES;

    GridView gridView;

    private moviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        moviesAdapter = new moviesAdapter(this , new ArrayList<movies>());
        gridView.setAdapter(moviesAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) { //NULL POINTER SHOULD COME FIRST ELSE THE APP WILL CRASH WITH AN NULL POINTER EXCEPTION
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        }
        else {
            TextView textView = findViewById(R.id.check_internet_textview);
            textView.setText(R.string.please_check_connection);
            textView.setVisibility(View.VISIBLE);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popular){
            REQUEST_URL = REQUEST_URL_POPULAR_MOVIES;
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, MainActivity.this);
            getSupportActionBar().setTitle(R.string.popular_movies);
            return true;
        }
        else if (id == R.id.top_rated){
            REQUEST_URL = REQUEST_URL_TOP_RATED_MOVIES;
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, MainActivity.this);
            getSupportActionBar().setTitle(R.string.top_rated_movies);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<movies>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(this , REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<movies>> loader, List<movies> movies) {

        // Clear the adapter of previous earthquake data
        moviesAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            moviesAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<movies>> loader) {
        // Loader reset, so we can clear out our existing data.
        moviesAdapter.clear();
    }
}