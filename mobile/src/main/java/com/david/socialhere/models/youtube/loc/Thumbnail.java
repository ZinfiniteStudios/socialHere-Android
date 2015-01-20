
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

public class Thumbnail {

    @Expose
    private String hqDefault;
    @Expose
    private String sqDefault;

    /**
     * 
     * @return
     *     The hqDefault
     */
    public String getHqDefault() {
        return hqDefault;
    }

    /**
     * 
     * @param hqDefault
     *     The hqDefault
     */
    public void setHqDefault(String hqDefault) {
        this.hqDefault = hqDefault;
    }

    /**
     * 
     * @return
     *     The sqDefault
     */
    public String getSqDefault() {
        return sqDefault;
    }

    /**
     * 
     * @param sqDefault
     *     The sqDefault
     */
    public void setSqDefault(String sqDefault) {
        this.sqDefault = sqDefault;
    }

}
