
package com.david.socialhere.models.twitter;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Hashtag {

    @Expose
    private String text;
    @Expose
    private List<Integer> indices = new ArrayList<Integer>();

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The indices
     */
    public List<Integer> getIndices() {
        return indices;
    }

    /**
     * 
     * @param indices
     *     The indices
     */
    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

}
