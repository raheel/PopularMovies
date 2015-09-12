package com.udacity.popularmovies.rest;

import com.udacity.popularmovies.GenreHelper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Raheel Khan on 8/21/2015.
 */
public interface MovieAPI {
    public static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3";
    public static final String API_KEY = "e32af3ba543806ff0b602d8389fd151c";

    @GET("/discover/movie")
    void getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, Callback<MovieResults> callback);

    @GET("/movie/{id}/videos")
    void getTrailers(@Query("api_key") String apiKey, @Path("id") Integer id, Callback<TrailerResults> callback);

    @GET("/movie/{id}/reviews")
    void getReviews(@Query("api_key") String apiKey, @Path("id") Integer id, Callback<ReviewResults> callback);

    @GET("/genre/movie/list")
    void getGenres(@Query("api_key") String apiKey, Callback<GenreHelper> callback);
}

