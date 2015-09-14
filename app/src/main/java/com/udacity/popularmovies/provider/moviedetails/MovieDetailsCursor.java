/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviedetails;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacity.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie_details} table.
 */
public class MovieDetailsCursor extends AbstractCursor implements MovieDetailsModel {
    public MovieDetailsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieDetailsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public int getMovieId() {
        Integer res = getIntegerOrNull(MovieDetailsColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * JSON of Movie Details
     * Cannot be {@code null}.
     */
    @NonNull
    public String getJson() {
        String res = getStringOrNull(MovieDetailsColumns.JSON);
        if (res == null)
            throw new NullPointerException("The value of 'json' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code popularity} value.
     */
    public double getPopularity() {
        Double res = getDoubleOrNull(MovieDetailsColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     */
    public double getVoteAverage() {
        Double res = getDoubleOrNull(MovieDetailsColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
