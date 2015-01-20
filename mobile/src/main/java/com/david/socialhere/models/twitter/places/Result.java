
package com.david.socialhere.models.twitter.places;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @Expose
    private List<Place> places = new ArrayList<Place>();

    /**
     * 
     * @return
     *     The places
     */
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * 
     * @param places
     *     The places
     */
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
