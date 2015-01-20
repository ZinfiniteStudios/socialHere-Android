
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

public class Item {

    @Expose
    private String author;
    @Expose
    private String id;
    @Expose
    private Integer position;
    @Expose
    private Video video;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
