package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

public class Thumbnail_ {

    @Expose
    private String hqDefault;
    @Expose
    private String sqDefault;

    public String getHqDefault() {
        return hqDefault;
    }

    public void setHqDefault(String hqDefault) {
        this.hqDefault = hqDefault;
    }

    public String getSqDefault() {
        return sqDefault;
    }

    public void setSqDefault(String sqDefault) {
        this.sqDefault = sqDefault;
    }

}
