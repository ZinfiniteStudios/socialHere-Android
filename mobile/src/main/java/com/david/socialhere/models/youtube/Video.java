
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

public class Video {

    @Expose
    private AccessControl accessControl;
    @Expose
    private String aspectRatio;
    @Expose
    private String category;
    @Expose
    private Integer commentCount;
    @Expose
    private Content_ content;
    @Expose
    private String description;
    @Expose
    private Integer duration;
    @Expose
    private Integer favoriteCount;
    @Expose
    private GeoCoordinates geoCoordinates;
    @Expose
    private String id;
    @Expose
    private String likeCount;
    @Expose
    private Player player;
    @Expose
    private Double rating;
    @Expose
    private Integer ratingCount;
    @Expose
    private Thumbnail thumbnail;
    @Expose
    private String title;
    @Expose
    private String updated;
    @Expose
    private String uploaded;
    @Expose
    private String uploader;
    @Expose
    private String viewCount;

    public AccessControl getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Content_ getContent() {
        return content;
    }

    public void setContent(Content_ content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public GeoCoordinates getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

}
