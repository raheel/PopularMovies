/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.movietrailers;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacity.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie_trailers} table.
 */
public class MovieTrailersContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieTrailersColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieTrailersSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieTrailersSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieTrailersContentValues putMovieId(int value) {
        mContentValues.put(MovieTrailersColumns.MOVIE_ID, value);
        return this;
    }


    public MovieTrailersContentValues putTrailerId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("trailerId must not be null");
        mContentValues.put(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }


    /**
     * JSON of Movie Trailers
     */
    public MovieTrailersContentValues putJson(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("json must not be null");
        mContentValues.put(MovieTrailersColumns.JSON, value);
        return this;
    }

}
