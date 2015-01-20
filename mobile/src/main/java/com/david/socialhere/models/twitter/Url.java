
package com.david.socialhere.models.twitter;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Url {

    @Expose
    private List<Url_> urls = new ArrayList<Url_>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url_> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url_> urls) {
        this.urls = urls;
    }

}
