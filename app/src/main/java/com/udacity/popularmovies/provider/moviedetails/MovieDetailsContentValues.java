/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviedetails;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacity.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie_details} table.
 */
public class MovieDetailsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieDetailsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieDetailsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieDetailsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieDetailsContentValues putMovieId(int value) {
        mContentValues.put(MovieDetailsColumns.MOVIE_ID, value);
        return this;
    }


    /**
     * JSON of Movie Details
     */
    public MovieDetailsContentValues putJson(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("json must not be null");
        mContentValues.put(MovieDetailsColumns.JSON, value);
        return this;
    }


    public MovieDetailsContentValues putPopularity(double value) {
        mContentValues.put(MovieDetailsColumns.POPULARITY, value);
        return this;
    }


    public MovieDetailsContentValues putVoteAverage(double value) {
        mContentValues.put(MovieDetailsColumns.VOTE_AVERAGE, value);
        return this;
    }

}
