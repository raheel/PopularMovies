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
import android.database.Cursor;
import android.net.Uri;

import com.udacity.popularmovies.provider.base.AbstractSelection;

/**
 * Selection for the {@code movie_trailers} table.
 */
public class MovieTrailersSelection extends AbstractSelection<MovieTrailersSelection> {
    @Override
    protected Uri baseUri() {
        return MovieTrailersColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieTrailersCursor} object, which is positioned before the first entry, or null.
     */
    public MovieTrailersCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieTrailersCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieTrailersCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieTrailersCursor} object, which is positioned before the first entry, or null.
     */
    public MovieTrailersCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieTrailersCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieTrailersCursor query(Context context) {
        return query(context, null);
    }


    public MovieTrailersSelection id(long... value) {
        addEquals("movie_trailers." + MovieTrailersColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieTrailersSelection idNot(long... value) {
        addNotEquals("movie_trailers." + MovieTrailersColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieTrailersSelection orderById(boolean desc) {
        orderBy("movie_trailers." + MovieTrailersColumns._ID, desc);
        return this;
    }

    public MovieTrailersSelection orderById() {
        return orderById(false);
    }

    public MovieTrailersSelection movieId(int... value) {
        addEquals(MovieTrailersColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieTrailersSelection movieIdNot(int... value) {
        addNotEquals(MovieTrailersColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieTrailersSelection movieIdGt(int value) {
        addGreaterThan(MovieTrailersColumns.MOVIE_ID, value);
        return this;
    }

    public MovieTrailersSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieTrailersColumns.MOVIE_ID, value);
        return this;
    }

    public MovieTrailersSelection movieIdLt(int value) {
        addLessThan(MovieTrailersColumns.MOVIE_ID, value);
        return this;
    }

    public MovieTrailersSelection movieIdLtEq(int value) {
        addLessThanOrEquals(MovieTrailersColumns.MOVIE_ID, value);
        return this;
    }

    public MovieTrailersSelection orderByMovieId(boolean desc) {
        orderBy(MovieTrailersColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieTrailersSelection orderByMovieId() {
        orderBy(MovieTrailersColumns.MOVIE_ID, false);
        return this;
    }

    public MovieTrailersSelection trailerId(String... value) {
        addEquals(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection trailerIdNot(String... value) {
        addNotEquals(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection trailerIdLike(String... value) {
        addLike(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection trailerIdContains(String... value) {
        addContains(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection trailerIdStartsWith(String... value) {
        addStartsWith(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection trailerIdEndsWith(String... value) {
        addEndsWith(MovieTrailersColumns.TRAILER_ID, value);
        return this;
    }

    public MovieTrailersSelection orderByTrailerId(boolean desc) {
        orderBy(MovieTrailersColumns.TRAILER_ID, desc);
        return this;
    }

    public MovieTrailersSelection orderByTrailerId() {
        orderBy(MovieTrailersColumns.TRAILER_ID, false);
        return this;
    }

    public MovieTrailersSelection json(String... value) {
        addEquals(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection jsonNot(String... value) {
        addNotEquals(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection jsonLike(String... value) {
        addLike(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection jsonContains(String... value) {
        addContains(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection jsonStartsWith(String... value) {
        addStartsWith(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection jsonEndsWith(String... value) {
        addEndsWith(MovieTrailersColumns.JSON, value);
        return this;
    }

    public MovieTrailersSelection orderByJson(boolean desc) {
        orderBy(MovieTrailersColumns.JSON, desc);
        return this;
    }

    public MovieTrailersSelection orderByJson() {
        orderBy(MovieTrailersColumns.JSON, false);
        return this;
    }
}
