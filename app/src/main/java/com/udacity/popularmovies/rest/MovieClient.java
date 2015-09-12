package com.udacity.popularmovies.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Raheel Khan on 8/19/2015.
 */
public class MovieClient {
    public static final String API_URL = "http://api.themoviedb.org/3";
    public static final String API_KEY = "e32af3ba543806ff0b602d8389fd151c";

    private static MovieAPI movieApi;

    static {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        movieApi = restAdapter.create(MovieAPI.class);
    }

    public static MovieAPI getMovieAPI() {
        return movieApi;
    }

}
