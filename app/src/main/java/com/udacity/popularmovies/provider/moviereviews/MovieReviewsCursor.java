/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviereviews;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacity.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie_reviews} table.
 */
public class MovieReviewsCursor extends AbstractCursor implements MovieReviewsModel {
    public MovieReviewsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieReviewsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public int getMovieId() {
        Integer res = getIntegerOrNull(MovieReviewsColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getReviewId() {
        String res = getStringOrNull(MovieReviewsColumns.REVIEW_ID);
        if (res == null)
            throw new NullPointerException("The value of 'review_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * JSON of Movie Reviews
     * Cannot be {@code null}.
     */
    @NonNull
    public String getJson() {
        String res = getStringOrNull(MovieReviewsColumns.JSON);
        if (res == null)
            throw new NullPointerException("The value of 'json' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
