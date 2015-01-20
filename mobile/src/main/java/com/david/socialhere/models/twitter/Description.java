
package com.david.socialhere.models.twitter;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Description {

    @Expose
    private List<Object> urls = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Object> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Object> urls) {
        this.urls = urls;
    }

}
