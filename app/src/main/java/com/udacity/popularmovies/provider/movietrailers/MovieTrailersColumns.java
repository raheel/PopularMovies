/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.movietrailers;

import android.net.Uri;
import android.provider.BaseColumns;

import com.udacity.popularmovies.provider.MovieProvider;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsColumns;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsColumns;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersColumns;

/**
 * Movie Trailers
 */
public class MovieTrailersColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie_trailers";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String TRAILER_ID = "trailer_id";

    /**
     * JSON of Movie Trailers
     */
    public static final String JSON = "json";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            TRAILER_ID,
            JSON
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(TRAILER_ID) || c.contains("." + TRAILER_ID)) return true;
            if (c.equals(JSON) || c.contains("." + JSON)) return true;
        }
        return false;
    }

}
