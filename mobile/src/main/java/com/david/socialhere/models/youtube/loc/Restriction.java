
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

public class Restriction {

    @Expose
    private String countries;
    @Expose
    private String relationship;
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The countries
     */
    public String getCountries() {
        return countries;
    }

    /**
     * 
     * @param countries
     *     The countries
     */
    public void setCountries(String countries) {
        this.countries = countries;
    }

    /**
     * 
     * @return
     *     The relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 
     * @param relationship
     *     The relationship
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
