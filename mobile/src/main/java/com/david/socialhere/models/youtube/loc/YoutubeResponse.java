
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

public class YoutubeResponse {

    @Expose
    private String apiVersion;
    @Expose
    private Data data;

    /**
     * 
     * @return
     *     The apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * 
     * @param apiVersion
     *     The apiVersion
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
