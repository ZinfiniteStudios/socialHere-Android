
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private String firstName;
    @Expose
    private String gender;
    @Expose
    private String id;
    @Expose
    private String lastName;
    @Expose
    private Photo photo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

}
