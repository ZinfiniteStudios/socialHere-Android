
package com.david.socialhere.models.tips;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @Expose
    private List<Tip> tips = new ArrayList<Tip>();

    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }

}
