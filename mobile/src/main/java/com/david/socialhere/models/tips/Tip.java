
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

public class Tip {

    @Expose
    private String canonicalUrl;
    @Expose
    private String createdAt;
    @Expose
    private String id;
    @Expose
    private Likes likes;
    @Expose
    private Boolean logView;
    @Expose
    private String text;
    @Expose
    private Todo todo;
    @Expose
    private String type;
    @Expose
    private User user;
    @Expose
    private Venue venue;

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Boolean getLogView() {
        return logView;
    }

    public void setLogView(Boolean logView) {
        this.logView = logView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

}
