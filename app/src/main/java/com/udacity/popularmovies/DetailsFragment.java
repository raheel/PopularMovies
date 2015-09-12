package com.udacity.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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

/**
 * Created by Raheel Khan on 9/11/2015.
 */
public class DetailsFragment extends Fragment {
        private final String LOG_TAG = DetailsFragment.class.getSimpleName();

        public DetailsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            System.out.println("******************************* abc DetailsFragment.onCreateView");
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);
            Intent intent = getActivity().getIntent();
            System.out.println("intent = " + intent);
            if (intent == null){
                return null;
            }
            Bundle bundle = intent.getExtras();
            if (bundle==null){
                bundle = getArguments();
            }

            if (intent != null && bundle != null && bundle.containsKey(Movie.class.getName())) {
                Movie movie = (Movie) bundle.getParcelable(Movie.class.getName());
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
                return null;
            }


            return rootView;
        }

        private void loadTrailersAndReviews(int id, Activity activity) {
            MovieAPI movieAPI = MovieClient.getMovieAPI();
            movieAPI.getTrailers(MovieClient.API_KEY, id, new TrailerCallback(activity));
            movieAPI.getReviews(MovieClient.API_KEY, id, new ReviewCallback(activity));
        }

        private static class TrailerCallback implements Callback<TrailerResults> {
            private Activity activity;
            private static GenreHelper genreHelper;

            public TrailerCallback(Activity activity) {
                this.activity = activity;
            }

            @Override
            public void success(TrailerResults results, Response response) {
                System.out.println("TrailerCallback.success");
                System.out.println("results = " + results.getTrailers().size());

                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.trailers);

                int count = 0;
                for (Trailer trailer : results.getTrailers()) {
                    count++;
                    ViewGroup item = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.list_item_trailer, null);
                    ViewGroup trailerContainer = (ViewGroup) item.findViewById(R.id.trailerContainer);
                    trailerContainer.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String url = "http://www.youtube.com/watch?v=" + (String) view.getTag();
                                    System.out.println("DetailsActivity.loadVideo");
                                    System.out.println("url = " + url);
                                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                                }
                            }
                    );
                    trailerContainer.setTag(trailer.getId());
                    TextView textView = (TextView) item.findViewById(R.id.trailer_name);
                    textView.setText(trailer.getName());

                    //remove separator for last item
                    if (count == results.getTrailers().size()) {
                        item.removeView(item.findViewById(R.id.trailer_separator));
                    }
                    parent.addView(item);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        }

        private static class ReviewCallback implements Callback<ReviewResults> {
            private Activity activity;

            public ReviewCallback(Activity activity) {
                this.activity = activity;
            }

            @Override
            public void success(ReviewResults results, Response response) {
                System.out.println("ReviewCallback.success");
                System.out.println("results = " + results.getReviews().size());

                LinearLayout parent = (LinearLayout) activity.findViewById(R.id.reviews);

                int count = 0;
                for (Review review : results.getReviews()) {
                    count++;
                    ViewGroup item = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.list_item_review, null);
                    TextView textView = (TextView) item.findViewById(R.id.review);
                    textView.setText(review.getAuthor() + " : " + review.getContent());

                    //remove separator for last item
                    if (count == results.getReviews().size()) {
                        item.removeView(item.findViewById(R.id.separator));
                    }
                    parent.addView(item);
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        }

        private static ViewGroup getViewGroup(Activity activity, ViewGroup root, int resource) {
            return (ViewGroup) activity.getLayoutInflater().inflate(resource, root, false);
        }


    public void onToggleFavorite(View view) {
        ImageButton button = (ImageButton) view.findViewById(R.id.favoriteButton);

        //view.ge
        System.out.println("view.getId()1 = " + view.getId());
        System.out.println("in toggle favorite.....");
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

            System.out.println("movie.getId() = " + movie.getId() + " - " + FavoritesHelper.isFavorited(this.getActivity(), movieId));
        }
    }

    }
