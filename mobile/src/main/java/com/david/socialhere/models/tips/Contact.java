
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

public class Contact {

    @Expose
    private String formattedPhone;
    @Expose
    private String phone;
    @Expose
    private String twitter;

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

}
