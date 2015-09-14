/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviereviews;

import com.udacity.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Movie Reviews
 */
public interface MovieReviewsModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * Get the {@code review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getReviewId();

    /**
     * JSON of Movie Reviews
     * Cannot be {@code null}.
     */
    @NonNull
    String getJson();
}
