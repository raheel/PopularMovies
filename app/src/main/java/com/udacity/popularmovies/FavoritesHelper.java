package com.udacity.popularmovies;

import android.app.Activity;
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
        editor.putString(FAVORITE_KEY, favoriteIds);
        editor.commit();
    }

    public static void removeFavorite(Context context, int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String favoriteIds = preferences.getString(FAVORITE_KEY, "");
        favoriteIds = favoriteIds.replaceAll(getFavoriteValue(id), "");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(FAVORITE_KEY, favoriteIds);
        editor.commit();
    }

    public static boolean isFavorited(Context context, int id) {
        String favoriteValue = getFavoriteValue(id);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String favoriteIds = preferences.getString(FAVORITE_KEY, "");
        return favoriteIds.contains(favoriteValue);
    }

    public static String getFavoriteValue(int id) {
        return FAVORITE_DELIMITER + id + FAVORITE_DELIMITER;
    }


    public static String getFavoriteIds(Activity activity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString(FavoritesHelper.FAVORITE_KEY, null);
    }

    public static boolean showFavoritesOnly(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(
                activity.getString(R.string.pref_favorites_key), false);
    }
}
