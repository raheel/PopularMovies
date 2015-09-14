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
import android.database.Cursor;
import android.net.Uri;

import com.udacity.popularmovies.provider.base.AbstractSelection;

/**
 * Selection for the {@code movie_reviews} table.
 */
public class MovieReviewsSelection extends AbstractSelection<MovieReviewsSelection> {
    @Override
    protected Uri baseUri() {
        return MovieReviewsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieReviewsCursor} object, which is positioned before the first entry, or null.
     */
    public MovieReviewsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieReviewsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieReviewsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieReviewsCursor} object, which is positioned before the first entry, or null.
     */
    public MovieReviewsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieReviewsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieReviewsCursor query(Context context) {
        return query(context, null);
    }


    public MovieReviewsSelection id(long... value) {
        addEquals("movie_reviews." + MovieReviewsColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieReviewsSelection idNot(long... value) {
        addNotEquals("movie_reviews." + MovieReviewsColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieReviewsSelection orderById(boolean desc) {
        orderBy("movie_reviews." + MovieReviewsColumns._ID, desc);
        return this;
    }

    public MovieReviewsSelection orderById() {
        return orderById(false);
    }

    public MovieReviewsSelection movieId(int... value) {
        addEquals(MovieReviewsColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieReviewsSelection movieIdNot(int... value) {
        addNotEquals(MovieReviewsColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieReviewsSelection movieIdGt(int value) {
        addGreaterThan(MovieReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieReviewsSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieReviewsSelection movieIdLt(int value) {
        addLessThan(MovieReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieReviewsSelection movieIdLtEq(int value) {
        addLessThanOrEquals(MovieReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieReviewsSelection orderByMovieId(boolean desc) {
        orderBy(MovieReviewsColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieReviewsSelection orderByMovieId() {
        orderBy(MovieReviewsColumns.MOVIE_ID, false);
        return this;
    }

    public MovieReviewsSelection reviewId(String... value) {
        addEquals(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection reviewIdNot(String... value) {
        addNotEquals(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection reviewIdLike(String... value) {
        addLike(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection reviewIdContains(String... value) {
        addContains(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection reviewIdStartsWith(String... value) {
        addStartsWith(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection reviewIdEndsWith(String... value) {
        addEndsWith(MovieReviewsColumns.REVIEW_ID, value);
        return this;
    }

    public MovieReviewsSelection orderByReviewId(boolean desc) {
        orderBy(MovieReviewsColumns.REVIEW_ID, desc);
        return this;
    }

    public MovieReviewsSelection orderByReviewId() {
        orderBy(MovieReviewsColumns.REVIEW_ID, false);
        return this;
    }

    public MovieReviewsSelection json(String... value) {
        addEquals(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection jsonNot(String... value) {
        addNotEquals(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection jsonLike(String... value) {
        addLike(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection jsonContains(String... value) {
        addContains(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection jsonStartsWith(String... value) {
        addStartsWith(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection jsonEndsWith(String... value) {
        addEndsWith(MovieReviewsColumns.JSON, value);
        return this;
    }

    public MovieReviewsSelection orderByJson(boolean desc) {
        orderBy(MovieReviewsColumns.JSON, desc);
        return this;
    }

    public MovieReviewsSelection orderByJson() {
        orderBy(MovieReviewsColumns.JSON, false);
        return this;
    }
}
