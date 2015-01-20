
package com.david.socialhere.models.twitter.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @Expose
    private Integer accuracy;
    @Expose
    private Boolean autocomplete;
    @Expose
    private String granularity;
    @Expose
    private String query;
    @SerializedName("trim_place")
    @Expose
    private Boolean trimPlace;

    /**
     * 
     * @return
     *     The accuracy
     */
    public Integer getAccuracy() {
        return accuracy;
    }

    /**
     * 
     * @param accuracy
     *     The accuracy
     */
    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * 
     * @return
     *     The autocomplete
     */
    public Boolean getAutocomplete() {
        return autocomplete;
    }

    /**
     * 
     * @param autocomplete
     *     The autocomplete
     */
    public void setAutocomplete(Boolean autocomplete) {
        this.autocomplete = autocomplete;
    }

    /**
     * 
     * @return
     *     The granularity
     */
    public String getGranularity() {
        return granularity;
    }

    /**
     * 
     * @param granularity
     *     The granularity
     */
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    /**
     * 
     * @return
     *     The query
     */
    public String getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The trimPlace
     */
    public Boolean getTrimPlace() {
        return trimPlace;
    }

    /**
     * 
     * @param trimPlace
     *     The trim_place
     */
    public void setTrimPlace(Boolean trimPlace) {
        this.trimPlace = trimPlace;
    }

}
