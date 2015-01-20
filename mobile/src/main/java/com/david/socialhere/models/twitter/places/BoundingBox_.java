
package com.david.socialhere.models.twitter.places;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class BoundingBox_ {

    @Expose
    private List<List<List<Double>>> coordinates = new ArrayList<List<List<Double>>>();
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The coordinates
     */
    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

}
