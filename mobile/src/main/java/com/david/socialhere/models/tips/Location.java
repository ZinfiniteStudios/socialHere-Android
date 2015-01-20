
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Location {

    @Expose
    private String address;
    @Expose
    private String cc;
    @Expose
    private String city;
    @Expose
    private String country;
    @Expose
    private Integer distance;
    @Expose
    private List<String> formattedAddress = new ArrayList<String>();
    @Expose
    private Double lat;
    @Expose
    private Double lng;
    @Expose
    private String postalCode;
    @Expose
    private String state;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
