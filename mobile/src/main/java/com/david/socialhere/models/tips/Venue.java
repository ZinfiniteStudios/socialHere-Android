
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Venue {

    @Expose
    private List<Category> categories = new ArrayList<Category>();
    @Expose
    private Contact contact;
    @Expose
    private String id;
    @Expose
    private Location location;
    @Expose
    private String name;
    @Expose
    private String ratingColor;
    @Expose
    private Stats stats;
    @Expose
    private String url;
    @Expose
    private Boolean verified;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
