package com.udacity.popularmovies;

/**
 * Created by Raheel Khan on 7/12/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {
    public static final String MOVIE_KEY = Movie.class.getName();
    private final String LOG_TAG = MovieFragment.class.getSimpleName();

    private static MovieAdapter movieAdapter;
    private static ArrayList<Movie> movies = new ArrayList<Movie>();

    private String sortPreference;
    public MovieFragment() {
    }

    private static class MovieFetchTask extends AsyncTask<String, String, Movie[]> {
        private ProgressDialog dialog;
        private Activity activity;

        private final String LOG_TAG = MovieFetchTask.class.getSimpleName();
        private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
        private static final String API_KEY = "e32af3ba543806ff0b602d8389fd151c";
        private static final String SORT_PARAM = "sort_by";
        private static final String API_PARAM = "api_key";


        public MovieFetchTask(Activity activity){
            this.activity = activity;
            this.dialog = new ProgressDialog(activity);
        }

        @Override
        protected Movie[] doInBackground(String[] params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;
            String sortBy = PreferenceManager.getDefaultSharedPreferences(activity).getString(
                    activity.getString(R.string.pref_sort_by_key),
                    activity.getString(R.string.pref_most_popular)
            ) + ".desc";

            try {
                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_PARAM, sortBy)
                        .appendQueryParameter(API_PARAM, API_KEY)
                        .build();

                Log.d(LOG_TAG, "URI: " + builtUri);

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    //for debugging purposes
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                movieJsonStr = buffer.toString();
                Log.d(LOG_TAG, "JSON: " + movieJsonStr);
            }catch(IOException e){
                Log.e(LOG_TAG, "Error " + e.getMessage() + " " + e.getStackTrace(), e);
                return null;
            }finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getMovieDataFromJson(movieJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage(activity.getResources().getString(R.string.loading_movies));
            dialog.show();
        }

        @Override
        protected void onPostExecute(Movie [] m) {
            if (movies==null){
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                return;
            }

            movies = new ArrayList<Movie>(Arrays.asList(m));
            movieAdapter.clear();

            for (Movie movie :  movies){
                movieAdapter.add(movie);
            }

            movieAdapter.notifyDataSetChanged();

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }


        private Movie[] getMovieDataFromJson(String movieJsonStr) throws JSONException {
            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");

            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < movieArray.length(); i++) {

                JSONObject movieObject = movieArray.getJSONObject(i);

                Movie movie = new Movie();
                movie.setId(movieObject.getInt("id"));
                movie.setTitle(movieObject.getString("title"));
                movie.setOriginalTitle(movieObject.getString("original_title"));
                movie.setOverview(movieObject.getString("overview"));
                movie.setBackdropPath(movieObject.getString("backdrop_path"));
                movie.setPosterPath(movieObject.getString("poster_path"));
                movie.setReleaseDate(movieObject.getString("release_date"));
                movie.setOriginalLanguage(movieObject.getString("original_language"));
                movie.setPopularity(movieObject.getDouble("popularity"));
                movie.setVoteAverage(movieObject.getInt("vote_average"));
                movie.setVoteCount(movieObject.getInt("vote_count"));
                movie.setAdult(movieObject.getBoolean("adult"));
                movie.setVideo(movieObject.getBoolean("video"));
                movies.add(movie);
            }

            return movies.toArray(new Movie[0]);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        movieAdapter = new MovieAdapter(getActivity(), movies);

        GridView gridView = (GridView)rootView.findViewById(R.id.grid_view);
        if (gridView!=null){
            gridView.setAdapter(movieAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MovieAdapter adapter = (MovieAdapter) parent.getAdapter();
                    Movie movie = adapter.getItem(position);
                    if (movie==null){
                        return;
                    }
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra(MOVIE_KEY, movie);
                    getActivity().startActivity(intent);
                }
            });
        }

        //
        return rootView;
    }

    @Override
    public void onResume() {
        //if shared preference has changed, then get data using the new sort order
        String sort = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(
                getActivity().getString(R.string.pref_sort_by_key),
                getActivity().getString(R.string.pref_most_popular));
        if (sortPreference==null){
            sortPreference = sort;
        }

        if (!sort.equals(sortPreference)){
            sortPreference = sort;
            new MovieFetchTask(getActivity()).execute();
        }
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null || !savedInstanceState.containsKey(MOVIE_KEY)) {
            new MovieFetchTask(getActivity()).execute();
        }
        else {
            List<Movie> movies = savedInstanceState.getParcelableArrayList(MOVIE_KEY);
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIE_KEY, movieAdapter.getMovies());
        super.onSaveInstanceState(outState);
    }
}


