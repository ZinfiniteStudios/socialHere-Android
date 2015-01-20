
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Likes {

    @Expose
    private Integer count;
    @Expose
    private List<Object> groups = new ArrayList<Object>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

}
