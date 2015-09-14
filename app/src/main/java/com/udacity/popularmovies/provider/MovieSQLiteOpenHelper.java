/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.udacity.popularmovies.BuildConfig;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsColumns;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsColumns;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersColumns;

public class MovieSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MovieSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static MovieSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MovieSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_MOVIE_DETAILS = "CREATE TABLE IF NOT EXISTS "
            + MovieDetailsColumns.TABLE_NAME + " ( "
            + MovieDetailsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieDetailsColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + MovieDetailsColumns.JSON + " TEXT NOT NULL, "
            + MovieDetailsColumns.POPULARITY + " REAL NOT NULL, "
            + MovieDetailsColumns.VOTE_AVERAGE + " REAL NOT NULL "
            + ", CONSTRAINT movie_id_constraint UNIQUE (movie_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_MOVIE_REVIEWS = "CREATE TABLE IF NOT EXISTS "
            + MovieReviewsColumns.TABLE_NAME + " ( "
            + MovieReviewsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieReviewsColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + MovieReviewsColumns.REVIEW_ID + " TEXT NOT NULL, "
            + MovieReviewsColumns.JSON + " TEXT NOT NULL "
            + ", CONSTRAINT review_id_constraint UNIQUE (review_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_MOVIE_TRAILERS = "CREATE TABLE IF NOT EXISTS "
            + MovieTrailersColumns.TABLE_NAME + " ( "
            + MovieTrailersColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieTrailersColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + MovieTrailersColumns.TRAILER_ID + " TEXT NOT NULL, "
            + MovieTrailersColumns.JSON + " TEXT NOT NULL "
            + ", CONSTRAINT trailer_id_constraint UNIQUE (trailer_id) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static MovieSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MovieSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static MovieSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context);
    }

    private MovieSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MovieSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MovieSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_DETAILS);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_REVIEWS);
        db.execSQL(SQL_CREATE_TABLE_MOVIE_TRAILERS);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
