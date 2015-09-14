/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.movietrailers;

import com.udacity.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Movie Trailers
 */
public interface MovieTrailersModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * Get the {@code trailer_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTrailerId();

    /**
     * JSON of Movie Trailers
     * Cannot be {@code null}.
     */
    @NonNull
    String getJson();
}
