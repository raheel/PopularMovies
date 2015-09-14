package com.udacity.popularmovies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreHelper {
    private static List<Genre> genres = new ArrayList<Genre>();
    private static Map<Integer, Genre> genreMap = new HashMap<>();

    static {
        genres.add(new Genre(28, "Action"));
        genres.add(new Genre(12, "Adventure"));
        genres.add(new Genre(16, "Animation"));
        genres.add(new Genre(35, "Comedy"));
        genres.add(new Genre(80, "Crime"));
        genres.add(new Genre(99, "Documentary"));
        genres.add(new Genre(18, "Drama"));
        genres.add(new Genre(10751, "Family"));
        genres.add(new Genre(14, "Fantasy"));
        genres.add(new Genre(10769, "Foreign"));
        genres.add(new Genre(36, "History"));
        genres.add(new Genre(27, "Horror"));
        genres.add(new Genre(10402, "Music"));
        genres.add(new Genre(9648, "Mystery"));
        genres.add(new Genre(10749, "Romance"));
        genres.add(new Genre(878, "Science Fiction"));
        genres.add(new Genre(10770, "TV Movie"));
        genres.add(new Genre(53, "Thriller"));
        genres.add(new Genre(10752, "War"));
        genres.add(new Genre(37, "Western"));

        for (Genre genre : genres) {
            genreMap.put(genre.getId(), genre);
        }
    }

    public static String getOutput(List<Integer> ids) {
        String output = "";
        int count = 0;
        for (int id : ids) {
            count++;
            output += genreMap.get(id) != null ? genreMap.get(id).getName() : "";
            if (count != ids.size()) {
                output += ", ";
            }
        }
        return output;
    }
}
