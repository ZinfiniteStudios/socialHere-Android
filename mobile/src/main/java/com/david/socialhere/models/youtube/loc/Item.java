
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @Expose
    private AccessControl accessControl;
    @Expose
    private String aspectRatio;
    @Expose
    private String category;
    @Expose
    private Integer commentCount;
    @Expose
    private Content content;
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
    private Integer viewCount;
    @Expose
    private String recorded;
    @Expose
    private List<Restriction> restrictions = new ArrayList<Restriction>();
    @Expose
    private Status status;

    /**
     * 
     * @return
     *     The accessControl
     */
    public AccessControl getAccessControl() {
        return accessControl;
    }

    /**
     * 
     * @param accessControl
     *     The accessControl
     */
    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    /**
     * 
     * @return
     *     The aspectRatio
     */
    public String getAspectRatio() {
        return aspectRatio;
    }

    /**
     * 
     * @param aspectRatio
     *     The aspectRatio
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 
     * @param commentCount
     *     The commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 
     * @return
     *     The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The favoriteCount
     */
    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    /**
     * 
     * @param favoriteCount
     *     The favoriteCount
     */
    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    /**
     * 
     * @return
     *     The geoCoordinates
     */
    public GeoCoordinates getGeoCoordinates() {
        return geoCoordinates;
    }

    /**
     * 
     * @param geoCoordinates
     *     The geoCoordinates
     */
    public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The likeCount
     */
    public String getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * @param likeCount
     *     The likeCount
     */
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 
     * @return
     *     The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * @param player
     *     The player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The ratingCount
     */
    public Integer getRatingCount() {
        return ratingCount;
    }

    /**
     * 
     * @param ratingCount
     *     The ratingCount
     */
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * 
     * @return
     *     The uploaded
     */
    public String getUploaded() {
        return uploaded;
    }

    /**
     * 
     * @param uploaded
     *     The uploaded
     */
    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    /**
     * 
     * @return
     *     The uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * 
     * @param uploader
     *     The uploader
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * 
     * @return
     *     The viewCount
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 
     * @param viewCount
     *     The viewCount
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 
     * @return
     *     The recorded
     */
    public String getRecorded() {
        return recorded;
    }

    /**
     * 
     * @param recorded
     *     The recorded
     */
    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    /**
     * 
     * @return
     *     The restrictions
     */
    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    /**
     * 
     * @param restrictions
     *     The restrictions
     */
    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
