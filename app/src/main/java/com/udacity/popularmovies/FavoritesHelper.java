package com.udacity.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Raheel Khan on 9/11/2015.
 */
public class FavoritesHelper {
    public static String FAVORITE_DELIMITER = "_";
    public static String FAVORITE_KEY = "favoriteIds";

    public static void addFavorite(Context context, int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String favoriteIds = preferences.getString(FAVORITE_KEY, "");
        favoriteIds += getFavoriteValue(id);
        SharedPreferences.Editor editor = preferences.edit();
        System.out.println("DetailsActivity.addFavorite: " + id);
        System.out.println("\tfavoriteIds = " + favoriteIds);
        editor.putString(FAVORITE_KEY, favoriteIds);
        editor.commit();
    }

    public static void removeFavorite(Context context, int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String favoriteIds = preferences.getString(FAVORITE_KEY, "");
        favoriteIds = favoriteIds.replaceAll(getFavoriteValue(id), "");
        System.out.println("DetailsActivity.removeFavorite: " + id + ", removing: " + getFavoriteValue(id));
        System.out.println("\tfavoriteIds after = " + favoriteIds);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(FAVORITE_KEY, favoriteIds);
        editor.commit();
    }

    public static boolean isFavorited(Context context, int id) {
        String favoriteValue = getFavoriteValue(id);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String favoriteIds = preferences.getString(FAVORITE_KEY, "");
        System.out.println("DetailsActivity.isFavorited: " + id);
        System.out.println("\tfavoriteIds = " + favoriteIds);
        return favoriteIds.contains(favoriteValue);
    }

    public static String getFavoriteValue(int id) {
        return FAVORITE_DELIMITER + id + FAVORITE_DELIMITER;
    }
}
