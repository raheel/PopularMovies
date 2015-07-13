package com.udacity.popularmovies;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Raheel Khan on 7/12/2015.
 */
public class Movie implements Parcelable {

    public static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    private int id;
    private String title;
    private String originalTitle;
    private String overview;
    private String backdropPath;
    private String posterPath;
    private String releaseDate;
    private String originalLanguage;
    private double popularity;
    private double voteAverage;
    private int voteCount;
    private boolean adult;
    private boolean video;

    public Movie(){}

    public Movie(Parcel source){
        id = source.readInt();
        title = source.readString();
        originalTitle = source.readString();
        overview = source.readString();
        backdropPath = source.readString();
        posterPath = source.readString();
        releaseDate = source.readString();
        originalLanguage = source.readString();
        popularity = source.readDouble();
        voteAverage = source.readDouble();
        voteCount = source.readInt();
        adult = source.readInt()==1 ? true : false;
        video = source.readInt()==1 ? true : false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeString(backdropPath);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(originalLanguage);
        dest.writeDouble(popularity);
        dest.writeDouble(voteAverage);
        dest.writeInt(voteCount);
        dest.writeInt(adult ? 1 : 0);
        dest.writeInt(video ? 1 : 0);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static Uri getPosterUri(Movie movie){
        return Uri.parse(IMAGE_BASE_URL)
                .buildUpon()
                .appendPath("w185")
                .appendEncodedPath(movie.getPosterPath())
                .build();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", popularity=" + popularity +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", adult=" + adult +
                ", video=" + video +
                '}';
    }
}
