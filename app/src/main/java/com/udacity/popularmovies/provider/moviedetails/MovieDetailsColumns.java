/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviedetails;

import android.net.Uri;
import android.provider.BaseColumns;

import com.udacity.popularmovies.provider.MovieProvider;
import com.udacity.popularmovies.provider.moviedetails.MovieDetailsColumns;
import com.udacity.popularmovies.provider.moviereviews.MovieReviewsColumns;
import com.udacity.popularmovies.provider.movietrailers.MovieTrailersColumns;

/**
 * Movie Details
 */
public class MovieDetailsColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie_details";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    /**
     * JSON of Movie Details
     */
    public static final String JSON = "json";

    public static final String POPULARITY = "popularity";

    public static final String VOTE_AVERAGE = "vote_average";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            JSON,
            POPULARITY,
            VOTE_AVERAGE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(JSON) || c.contains("." + JSON)) return true;
            if (c.equals(POPULARITY) || c.contains("." + POPULARITY)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
        }
        return false;
    }

}
