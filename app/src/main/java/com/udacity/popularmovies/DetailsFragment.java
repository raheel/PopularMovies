package com.udacity.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsCursor;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsSelection;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsContentValues;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsCursor;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsSelection;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersContentValues;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersCursor;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersSelection;
import com.udacity.popularmovies.rest.Movie;
import com.udacity.popularmovies.rest.MovieAPI;
import com.udacity.popularmovies.rest.MovieClient;
import com.udacity.popularmovies.rest.Review;
import com.udacity.popularmovies.rest.ReviewResults;
import com.udacity.popularmovies.rest.Trailer;
import com.udacity.popularmovies.rest.TrailerResults;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.support.v7.widget.ShareActionProvider;

/**
 * Created by Raheel Khan on 9/11/2015.
 */
public class DetailsFragment extends Fragment {
        private final String LOG_TAG = DetailsFragment.class.getSimpleName();
        private ShareActionProvider shareActionProvider;
        private Movie movie;
        private Menu menu;

    public DetailsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            setHasOptionsMenu(true);

            View rootView = inflater.inflate(R.layout.fragment_details, container, false);
            Intent intent = getActivity().getIntent();

            if (intent == null){
                return null;
            }
            Bundle bundle = intent.getExtras();
            if (bundle==null){
                bundle = getArguments();
            }

            if (intent != null && bundle != null && bundle.containsKey(Movie.class.getName())) {
                movie = (Movie) bundle.getParcelable(Movie.class.getName());
                ((TextView) rootView.findViewById(R.id.title)).setText(movie.getTitle());
                ((TextView) rootView.findViewById(R.id.release_date)).setText(movie.getReleaseDate());
                ((TextView) rootView.findViewById(R.id.genres)).setText(movie.getGenres());
                ((RatingBar) rootView.findViewById(R.id.rating_bar)).setRating(movie.getVoteAverage().floatValue());
                ((TextView) rootView.findViewById(R.id.vote_count)).setText(movie.getVoteCount() + "");
                ((TextView) rootView.findViewById(R.id.synopsis)).setText(movie.getOverview());

                ImageButton favoriteButton = (ImageButton) rootView.findViewById(R.id.favoriteButton);
                if (FavoritesHelper.isFavorited(this.getActivity(), movie.getId())) {
                    favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
                }
                favoriteButton.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                          onToggleFavorite(view);
                        }
                });

                ImageView posterView = (ImageView) rootView.findViewById(R.id.poster);
                Picasso.with(getActivity()).load(Movie.getPosterUri(movie)).into(posterView);

                loadTrailersAndReviews(movie.getId(), getActivity());
            }
            else{
                return inflater.inflate(R.layout.blank_details, container, false);
            }


            return rootView;
        }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_details, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.share_trailer);

        // Get the provider and hold onto it to set/change the share intent.
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        //hide the action bar right now and show once there are any trailers availabe
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar!=null){
            actionBar.setTitle(R.string.details_heading );
            actionBar.hide();
        }
    }

        private void loadTrailersAndReviews(int id, Activity activity) {
            MovieAPI movieAPI = MovieClient.getMovieAPI();
            movieAPI.getTrailers(MovieClient.API_KEY, id, new TrailerCallback(activity, id));
            movieAPI.getReviews(MovieClient.API_KEY, id, new ReviewCallback(activity, id));
        }

        private class TrailerCallback implements Callback<TrailerResults> {
            private Activity activity;
            private int movieId;

            public TrailerCallback(Activity activity, int movieId) {
                this.activity = activity;
                this.movieId = movieId;
            }

            @Override
            public void success(TrailerResults results, Response response) {
                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.trailers);

                int count = 0;
                for (Trailer trailer : results.getTrailers()) {

                    if (count==0){
                        shareTrailer(trailer.getKey());
                    }
                    //insert into DB
                    MovieTrailersContentValues values = new MovieTrailersContentValues();
                    values.putJson(new Gson().toJson(trailer));
                    values.putMovieId(movieId);
                    values.putTrailerId(trailer.getId());
                    Uri uri = values.insert(activity.getContentResolver());

                    count++;

                    addRow(parent, count==results.getTrailers().size(), trailer);
                }
                //remove Trailers heading if none found
                if (count==0){
                    removeHeader();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                MovieTrailersSelection selection = new MovieTrailersSelection();
                selection.movieId(movieId);
                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.trailers);
    
                MovieTrailersCursor movieTrailers = selection.query(activity.getContentResolver());
                int count = 0;
                int size = movieTrailers.getCount();

                while (movieTrailers.moveToNext()) {
                    Trailer movieTrailer = new Gson().fromJson(movieTrailers.getJson(), Trailer.class);
                    if (count==0){
                        shareTrailer(movieTrailer.getKey());
                    }
                    count++;
                    this.addRow(parent, count==size, movieTrailer);
                }

                //remove Trailers heading if none found
                if (count==0){
                    removeHeader();
                }
            }

            private void addRow(LinearLayout parent, boolean last, Trailer trailer) {
                ViewGroup item = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.list_item_trailer, null);
                ViewGroup trailerContainer = (ViewGroup) item.findViewById(R.id.trailerContainer);
                trailerContainer.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = getYouTubeUrl((String) view.getTag());
                                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                            }
                        }
                );
                trailerContainer.setTag(trailer.getKey());
                TextView textView = (TextView) item.findViewById(R.id.trailer_name);
                textView.setText(trailer.getName());

                //remove separator for last item
                if (last) {
                    item.removeView(item.findViewById(R.id.trailer_separator));
                }

                parent.addView(item);
            }

            public void removeHeader(){
                View trailersHeading = activity.findViewById(R.id.trailers_heading);
                ViewGroup root = (ViewGroup) trailersHeading.getParent();
                root.removeView(trailersHeading);
            }

            private void shareTrailer(String key) {
                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar!=null){
                    actionBar.show();
                }
                String trailerUrl = getYouTubeUrl(key);
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                shareIntent.setType("text/plain");
                String shareText = String.format(getActivity().getString(R.string.check_out_trailer) + " " + movie.getTitle() + ": " + trailerUrl);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                shareActionProvider.setShareIntent(shareIntent);
            }

            private String getYouTubeUrl(String key){
                return "http://www.youtube.com/watch?v=" + key;
            }
        }

        private class ReviewCallback implements Callback<ReviewResults> {
            private Activity activity;
            private int movieId;

            public ReviewCallback(Activity activity, int movieId) {
                this.activity = activity;
                this.movieId = movieId;
            }

            @Override
            public void success(ReviewResults results, Response response) {
                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.reviews);

                int count = 0;
                for (Review review : results.getReviews()) {
                    //insert into DB
                    MovieReviewsContentValues values = new MovieReviewsContentValues();
                    values.putJson(new Gson().toJson(review));
                    values.putMovieId(movieId);
                    values.putReviewId(review.getId());
                    Uri uri = values.insert(activity.getContentResolver());
                    count++;
                    addRow(parent, count==results.getReviews().size(), review);
                }
                //remove Reviews heading if none found
                if (count==0){
                    removeHeading();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.reviews);

                MovieReviewsSelection selection = new MovieReviewsSelection();
                selection.movieId(movieId);

                MovieReviewsCursor movieReviews = selection.query(activity.getContentResolver());
                int count = 0;
                int size = movieReviews.getCount();

                while (movieReviews.moveToNext()) {
                    count++;
                    Review movieReview = new Gson().fromJson(movieReviews.getJson(), Review.class);
                    this.addRow(parent, count==size, movieReview);
                }
                //remove Reviews heading if none found
                if (count==0){
                    removeHeading();
                }
            }

            private void addRow(LinearLayout parent, boolean last, Review review) {
                ViewGroup item = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.list_item_review, null);
                TextView textView = (TextView) item.findViewById(R.id.review);
                textView.setText(review.getAuthor() + " : " + review.getContent());

                //remove separator for last item
                if (last) {
                    item.removeView(item.findViewById(R.id.separator));
                }
                parent.addView(item);
            }

            private void removeHeading() {
                View reviewsHeading = activity.findViewById(R.id.reviews_heading);
                ViewGroup root = (ViewGroup) reviewsHeading.getParent();
                root.removeView(reviewsHeading);
            }
        }

        private static ViewGroup getViewGroup(Activity activity, ViewGroup root, int resource) {
            return (ViewGroup) activity.getLayoutInflater().inflate(resource, root, false);
        }


    public void onToggleFavorite(View view) {
        ImageButton button = (ImageButton) view.findViewById(R.id.favoriteButton);
        Intent intent = this.getActivity().getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle==null){
            bundle = getArguments();
        }

        if (intent != null && bundle != null && bundle.containsKey(Movie.class.getName())) {
            Movie movie = (Movie) bundle.getParcelable(Movie.class.getName());
            int movieId = movie.getId();
            if (FavoritesHelper.isFavorited(this.getActivity(), movieId)) {
                button.setImageResource(android.R.drawable.btn_star_big_off);
                FavoritesHelper.removeFavorite(this.getActivity(), movie.getId());
            } else {
                button.setImageResource(android.R.drawable.btn_star_big_on);
                FavoritesHelper.addFavorite(this.getActivity(), movie.getId());
            }
        }
    }

    }
