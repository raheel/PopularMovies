package com.udacity.popularmovies.provider.moviedetails;

import android.database.Cursor;

/**
 * Created by Raheel Khan on 9/8/2015.
 */
public class ProviderHelper {
    public static boolean isEmpty(Cursor cursor){
        return cursor==null || !cursor.moveToFirst();
    }
}
