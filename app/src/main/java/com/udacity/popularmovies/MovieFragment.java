package com.udacity.popularmovies;

/**
 * Created by Raheel Khan on 7/12/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsContentValues;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsCursor;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsSelection;
import com.udacity.popularmovies.rest.Movie;
import com.udacity.popularmovies.rest.MovieAPI;
import com.udacity.popularmovies.rest.MovieClient;
import com.udacity.popularmovies.rest.MovieResults;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {
    public static final String MOVIE_KEY = Movie.class.getName();
    private final String LOG_TAG = MovieFragment.class.getSimpleName();

    private static MovieAdapter movieAdapter;
    private static ArrayList<Movie> movies = new ArrayList<Movie>();

    private ProgressDialog dialog;

    private String sortPreference;
    private boolean showFavoritesOnly;
    private boolean first = true;
    private String favoriteIds;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("container = " + container + " twoPane, " + isTwoPaneMode());
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        movieAdapter = new MovieAdapter(getActivity(), movies);
        System.out.println("movies = " + movies);
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        System.out.println("gridView = " + gridView);
        if (gridView != null) {
            gridView.setAdapter(movieAdapter);
            System.out.println("inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MovieAdapter adapter = (MovieAdapter) parent.getAdapter();
                    Movie movie = adapter.getItem(position);
                    System.out.println("gridview: movie = " + movie);
                    System.out.println("onClick: twoPane = " + isTwoPaneMode());
                    if (movie == null) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(MOVIE_KEY, movie);

                    if (isTwoPaneMode()){
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        DetailsFragment fragment = new DetailsFragment();
                        fragment.setArguments(bundle);
                        ft.replace(R.id.movie_detail_container, fragment);
                        //FragmentTransaction fragmentTransaction = ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                    }
                    else {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                    }
                }
            });
        }

        //
        return rootView;
    }

    @Override
    public void onResume() {
        System.out.println("*******************************8MovieFragment.onResume");
        //if shared preference has changed, then get data using the new sort order
        String sort = getSortByDesc(getActivity());
        System.out.println("onResume:  = " + sort);
        boolean favorite = showFavoritesOnly(getActivity());
        boolean hasFavoriteSelectionChanged = false;
        if(first || (favorite != showFavoritesOnly)){
            hasFavoriteSelectionChanged = true;
            showFavoritesOnly = favorite;
            first = false;
        }




        System.out.println("MovieFragment.onResume");
        boolean hasSortChanged = !sort.equals(sortPreference);
        boolean haveFavoritesChanged = haveFavoritesChanged(getActivity());
        sortPreference = sort;



        showFavoritesOnly = favorite;
        if (hasSortChanged){
            if (showFavoritesOnly){
                showLoadingMoviesDialog();
                populateFromDB(getActivity(), dialog);
                System.out.println("onResume: Part 1");

            }
            else{
                showLoadingMoviesDialog();
                MovieAPI movieAPI = MovieClient.getMovieAPI();
                movieAPI.getMovies(MovieClient.API_KEY, sortPreference, new MovieCallback(getActivity(), dialog));
                System.out.println("onResume: Part 2");

            }
        }
        else{
            if (hasFavoriteSelectionChanged || haveFavoritesChanged){
                if (showFavoritesOnly){
                    showLoadingMoviesDialog();
                    populateFromDB(getActivity(), dialog);
                    System.out.println("onResume: Part 3");

                }
                else{
                    showLoadingMoviesDialog();
                    MovieAPI movieAPI = MovieClient.getMovieAPI();
                    movieAPI.getMovies(MovieClient.API_KEY, sortPreference, new MovieCallback(getActivity(), dialog));
                    System.out.println("onResume: Part 4");

                }
            }
            else{
                System.out.println("onResume: Part 5");
            }
        }


        super.onResume();
    }

    private boolean haveFavoritesChanged(Activity activity) {
        String ids = getFavoriteIds(activity);
        if (ids != null && !ids.equals(favoriteIds)) {
            this.favoriteIds = ids;
            return true;
        }
        return false;
    }

    private void showLoadingMoviesDialog(){
        if (dialog==null) {
            dialog = new ProgressDialog(getActivity());
        }
        dialog.setMessage(getActivity().getResources().getString(R.string.loading_movies));
        dialog.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null || !savedInstanceState.containsKey(MOVIE_KEY)) {
            System.out.println("***********MovieFragment.onCreate: in if");

            String sortBy = getSortByDesc(getActivity());
            System.out.println("sortBy = " + sortBy);

            showLoadingMoviesDialog();

            MovieAPI movieAPI = MovieClient.getMovieAPI();
            movieAPI.getMovies(MovieClient.API_KEY, sortPreference, new MovieCallback(getActivity(), dialog));
        } else {
            System.out.println("***********MovieFragment.onCreate: in else");
            List<Movie> movies = savedInstanceState.getParcelableArrayList(MOVIE_KEY);
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIE_KEY, movieAdapter.getMovies());
        super.onSaveInstanceState(outState);
    }

    private class MovieCallback implements Callback<MovieResults> {
        private ProgressDialog dialog;
        private Activity activity;

        private MovieCallback(Activity activity, ProgressDialog dialog) {
            this.activity = activity;
            this.dialog = dialog;
        }

        @Override
        public void success(MovieResults results, Response response) {
            movieAdapter.clear();
            String json = new String(((TypedByteArray) response.getBody()).getBytes());
            //      System.out.println("********moviecallback....results = " + json) ;
            favoriteIds = getFavoriteIds(this.activity);
            System.out.println("MovieCallback.success");
            System.out.println("favoriteIds = " + favoriteIds);
            System.out.println("movies = " + results.getMovies());
            for (Movie movie : results.getMovies()) {
                System.out.println("\t\t ------ movie = " + 1);

                MovieDetailsContentValues values = new MovieDetailsContentValues();
                values.putJson(new Gson().toJson(movie));
                values.putMovieId(movie.getId());
                values.putPopularity(movie.getPopularity());
                values.putVoteAverage(movie.getVoteAverage());
                Uri uri = values.insert(activity.getContentResolver());
                System.out.println("uri = " + uri);
                movie.setGenres(GenreHelper.getOutput(movie.getGenreIds()));
                if (shouldAddMovie(getActivity(), favoriteIds, movie)) {
                    movieAdapter.add(movie);
                }
            }

            movieAdapter.notifyDataSetChanged();

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }


        @Override
        public void failure(RetrofitError error) {
            populateFromDB(this.activity, this.dialog);
        }
    }


    private String getSortByDesc(Activity activity) {
        return getSortBy(activity) + ".desc";
    }

    private String getSortBy(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(
                getActivity().getString(R.string.pref_sort_by_key),
                getActivity().getString(R.string.pref_most_popular)
        );
    }


    private boolean showFavoritesOnly(Activity activity) {
        System.out.println("activity = [" + activity + "]");
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(
                activity.getString(R.string.pref_favorites_key), false);
    }

    private void populateFromDB(Activity activity, ProgressDialog dialog) {
        movieAdapter.clear();
        MovieDetailsSelection selection = new MovieDetailsSelection();
        String sortBy = getSortBy(activity);
        System.out.println("onResume: populateFromDB: sortBy = " + sortBy);
        if ("popularity".equals(sortBy)) {
            selection.orderByPopularity(true);
        } else {
            selection.orderByVoteAverage(true);
        }
        String favoriteIds = getFavoriteIds(getActivity());
        MovieDetailsCursor movieDetails = selection.query(activity.getContentResolver());
        int count = 0;
        while (movieDetails.moveToNext()) {
            System.out.println("movieDetails.getId() = " + movieDetails.getId() + ", popularity: " + movieDetails.getPopularity() + ", vote average: " + movieDetails.getVoteAverage());
            Movie movie = new Gson().fromJson(movieDetails.getJson(), Movie.class);
            movie.setGenres(GenreHelper.getOutput(movie.getGenreIds()));
            if (shouldAddMovie(getActivity(), favoriteIds, movie)) {
                count++;
                movieAdapter.add(movie);
            }
        }

        movieAdapter.notifyDataSetChanged();

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        //no favorites
        if (count == 0 && showFavoritesOnly(getActivity())) {
            Toast.makeText(getActivity(), getActivity().getString(R.string.no_favorites), Toast.LENGTH_SHORT).show();
        }
        //favorites-mode off and no movies from the database
        else
        if (count==0){
            Toast.makeText(getActivity(), getActivity().getString(R.string.loading_movies_error), Toast.LENGTH_SHORT).show();
        }
    }


    private String getFavoriteIds(Activity activity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString(FavoritesHelper.FAVORITE_KEY, null);
    }

    private boolean shouldAddMovie(Activity activity, String favoriteIds, Movie movie) {
        boolean showFavoritesOnly = showFavoritesOnly(activity);
        System.out.println("MovieCallback.shouldAddMovie");
        System.out.println("showFavoritesOnly = " + showFavoritesOnly);
        System.out.println("favoriteIds = " + favoriteIds);
        if (!showFavoritesOnly) {
            return true;
        }

        if (favoriteIds != null) {
            return favoriteIds.contains(FavoritesHelper.getFavoriteValue(movie.getId()));
        }

        return false;
    }

    private boolean isTwoPaneMode(){
        return (getResources().getBoolean(R.bool.two_pane_mode));
    }

}



