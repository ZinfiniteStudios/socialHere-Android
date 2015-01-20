
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

public class GeoCoordinates {

    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
