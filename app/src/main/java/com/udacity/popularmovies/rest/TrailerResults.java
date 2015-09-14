
package com.udacity.popularmovies.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrailerResults {

    @Expose
    private Integer id;
    @Expose
    @SerializedName("results")
    private List<Trailer> trailers = new ArrayList<Trailer>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The results
     */
    public List<Trailer> getTrailers() {
        return trailers;
    }

    /**
     * @param trailers The results
     */
    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

}
