
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

public class Todo {

    @Expose
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
