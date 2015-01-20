
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

public class Stats {

    @Expose
    private Integer checkinsCount;
    @Expose
    private Integer tipCount;
    @Expose
    private Integer usersCount;

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public Integer getTipCount() {
        return tipCount;
    }

    public void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

}
