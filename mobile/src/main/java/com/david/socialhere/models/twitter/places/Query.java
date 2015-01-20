
package com.david.socialhere.models.twitter.places;

import com.google.gson.annotations.Expose;

public class Query {

    @Expose
    private Params params;
    @Expose
    private String type;
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The params
     */
    public Params getParams() {
        return params;
    }

    /**
     * 
     * @param params
     *     The params
     */
    public void setParams(Params params) {
        this.params = params;
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

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
