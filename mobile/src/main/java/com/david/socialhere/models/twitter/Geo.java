package com.david.socialhere.models.twitter;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidhodge on 12/16/14.
 */
public class Geo {
    @Expose
    private List<Double> coordinates = new ArrayList<Double>();
    @Expose
    private String type;

    /**
     *
     * @return
     * The coordinates
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param coordinates
     * The coordinates
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }
}
