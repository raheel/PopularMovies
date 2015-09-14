/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.popularmovies.BuildConfig;
import com.udacity.popularmovies.provider.base.BaseContentProvider;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsColumns;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsColumns;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersColumns;

public class MovieProvider extends BaseContentProvider {
    private static final String TAG = MovieProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.udacity.popularmovies.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_MOVIE_DETAILS = 0;
    private static final int URI_TYPE_MOVIE_DETAILS_ID = 1;

    private static final int URI_TYPE_MOVIE_REVIEWS = 2;
    private static final int URI_TYPE_MOVIE_REVIEWS_ID = 3;

    private static final int URI_TYPE_MOVIE_TRAILERS = 4;
    private static final int URI_TYPE_MOVIE_TRAILERS_ID = 5;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, MovieDetailsColumns.TABLE_NAME, URI_TYPE_MOVIE_DETAILS);
        URI_MATCHER.addURI(AUTHORITY, MovieDetailsColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_DETAILS_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieReviewsColumns.TABLE_NAME, URI_TYPE_MOVIE_REVIEWS);
        URI_MATCHER.addURI(AUTHORITY, MovieReviewsColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_REVIEWS_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieTrailersColumns.TABLE_NAME, URI_TYPE_MOVIE_TRAILERS);
        URI_MATCHER.addURI(AUTHORITY, MovieTrailersColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_TRAILERS_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return MovieSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_MOVIE_DETAILS:
                return TYPE_CURSOR_DIR + MovieDetailsColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_DETAILS_ID:
                return TYPE_CURSOR_ITEM + MovieDetailsColumns.TABLE_NAME;

            case URI_TYPE_MOVIE_REVIEWS:
                return TYPE_CURSOR_DIR + MovieReviewsColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_REVIEWS_ID:
                return TYPE_CURSOR_ITEM + MovieReviewsColumns.TABLE_NAME;

            case URI_TYPE_MOVIE_TRAILERS:
                return TYPE_CURSOR_DIR + MovieTrailersColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_TRAILERS_ID:
                return TYPE_CURSOR_ITEM + MovieTrailersColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_MOVIE_DETAILS:
            case URI_TYPE_MOVIE_DETAILS_ID:
                res.table = MovieDetailsColumns.TABLE_NAME;
                res.idColumn = MovieDetailsColumns._ID;
                res.tablesWithJoins = MovieDetailsColumns.TABLE_NAME;
                res.orderBy = MovieDetailsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE_REVIEWS:
            case URI_TYPE_MOVIE_REVIEWS_ID:
                res.table = MovieReviewsColumns.TABLE_NAME;
                res.idColumn = MovieReviewsColumns._ID;
                res.tablesWithJoins = MovieReviewsColumns.TABLE_NAME;
                res.orderBy = MovieReviewsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE_TRAILERS:
            case URI_TYPE_MOVIE_TRAILERS_ID:
                res.table = MovieTrailersColumns.TABLE_NAME;
                res.idColumn = MovieTrailersColumns._ID;
                res.tablesWithJoins = MovieTrailersColumns.TABLE_NAME;
                res.orderBy = MovieTrailersColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_MOVIE_DETAILS_ID:
            case URI_TYPE_MOVIE_REVIEWS_ID:
            case URI_TYPE_MOVIE_TRAILERS_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
