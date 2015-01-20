
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

public class Youtube {

    @Expose
    private String apiVersion;
    @Expose
    private Data data;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
