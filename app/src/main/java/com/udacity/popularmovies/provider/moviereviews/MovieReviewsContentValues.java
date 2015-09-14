/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviereviews;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacity.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie_reviews} table.
 */
public class MovieReviewsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieReviewsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieReviewsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieReviewsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieReviewsContentValues putMovieId(int value) {
        mContentValues.put(MovieReviewsColumns.MOVIE_ID, value);
        return this;
    }


    public MovieReviewsContentValues putReviewId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("reviewId must not be null");
        mContentValues.put(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }


    /**
     * JSON of Movie Reviews
     */
    public MovieReviewsContentValues putJson(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("json must not be null");
        mContentValues.put(MovieReviewsColumns.JSON, value);
        return this;
    }

}
