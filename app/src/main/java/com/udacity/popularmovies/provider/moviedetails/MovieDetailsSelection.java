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
import android.database.Cursor;
import android.net.Uri;

import com.udacity.popularmovies.provider.base.AbstractSelection;

/**
 * Selection for the {@code movie_details} table.
 */
public class MovieDetailsSelection extends AbstractSelection<MovieDetailsSelection> {
    @Override
    protected Uri baseUri() {
        return MovieDetailsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieDetailsCursor} object, which is positioned before the first entry, or null.
     */
    public MovieDetailsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieDetailsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieDetailsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieDetailsCursor} object, which is positioned before the first entry, or null.
     */
    public MovieDetailsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieDetailsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieDetailsCursor query(Context context) {
        return query(context, null);
    }


    public MovieDetailsSelection id(long... value) {
        addEquals("movie_details." + MovieDetailsColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection idNot(long... value) {
        addNotEquals("movie_details." + MovieDetailsColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection orderById(boolean desc) {
        orderBy("movie_details." + MovieDetailsColumns._ID, desc);
        return this;
    }

    public MovieDetailsSelection orderById() {
        return orderById(false);
    }

    public MovieDetailsSelection movieId(int... value) {
        addEquals(MovieDetailsColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection movieIdNot(int... value) {
        addNotEquals(MovieDetailsColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection movieIdGt(int value) {
        addGreaterThan(MovieDetailsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieDetailsSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieDetailsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieDetailsSelection movieIdLt(int value) {
        addLessThan(MovieDetailsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieDetailsSelection movieIdLtEq(int value) {
        addLessThanOrEquals(MovieDetailsColumns.MOVIE_ID, value);
        return this;
    }

    public MovieDetailsSelection orderByMovieId(boolean desc) {
        orderBy(MovieDetailsColumns.MOVIE_ID, desc);
        return this;
    }

    public MovieDetailsSelection orderByMovieId() {
        orderBy(MovieDetailsColumns.MOVIE_ID, false);
        return this;
    }

    public MovieDetailsSelection json(String... value) {
        addEquals(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection jsonNot(String... value) {
        addNotEquals(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection jsonLike(String... value) {
        addLike(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection jsonContains(String... value) {
        addContains(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection jsonStartsWith(String... value) {
        addStartsWith(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection jsonEndsWith(String... value) {
        addEndsWith(MovieDetailsColumns.JSON, value);
        return this;
    }

    public MovieDetailsSelection orderByJson(boolean desc) {
        orderBy(MovieDetailsColumns.JSON, desc);
        return this;
    }

    public MovieDetailsSelection orderByJson() {
        orderBy(MovieDetailsColumns.JSON, false);
        return this;
    }

    public MovieDetailsSelection popularity(double... value) {
        addEquals(MovieDetailsColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection popularityNot(double... value) {
        addNotEquals(MovieDetailsColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection popularityGt(double value) {
        addGreaterThan(MovieDetailsColumns.POPULARITY, value);
        return this;
    }

    public MovieDetailsSelection popularityGtEq(double value) {
        addGreaterThanOrEquals(MovieDetailsColumns.POPULARITY, value);
        return this;
    }

    public MovieDetailsSelection popularityLt(double value) {
        addLessThan(MovieDetailsColumns.POPULARITY, value);
        return this;
    }

    public MovieDetailsSelection popularityLtEq(double value) {
        addLessThanOrEquals(MovieDetailsColumns.POPULARITY, value);
        return this;
    }

    public MovieDetailsSelection orderByPopularity(boolean desc) {
        orderBy(MovieDetailsColumns.POPULARITY, desc);
        return this;
    }

    public MovieDetailsSelection orderByPopularity() {
        orderBy(MovieDetailsColumns.POPULARITY, false);
        return this;
    }

    public MovieDetailsSelection voteAverage(double... value) {
        addEquals(MovieDetailsColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection voteAverageNot(double... value) {
        addNotEquals(MovieDetailsColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieDetailsSelection voteAverageGt(double value) {
        addGreaterThan(MovieDetailsColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieDetailsSelection voteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieDetailsColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieDetailsSelection voteAverageLt(double value) {
        addLessThan(MovieDetailsColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieDetailsSelection voteAverageLtEq(double value) {
        addLessThanOrEquals(MovieDetailsColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieDetailsSelection orderByVoteAverage(boolean desc) {
        orderBy(MovieDetailsColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieDetailsSelection orderByVoteAverage() {
        orderBy(MovieDetailsColumns.VOTE_AVERAGE, false);
        return this;
    }
}
