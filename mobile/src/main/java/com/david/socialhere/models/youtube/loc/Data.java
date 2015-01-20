
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @Expose
    private List<Item> items = new ArrayList<Item>();
    @Expose
    private Integer itemsPerPage;
    @Expose
    private Integer startIndex;
    @Expose
    private Integer totalItems;
    @Expose
    private String updated;

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The itemsPerPage
     */
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * 
     * @param itemsPerPage
     *     The itemsPerPage
     */
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * 
     * @return
     *     The startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 
     * @return
     *     The totalItems
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * 
     * @param totalItems
     *     The totalItems
     */
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    /**
     * 
     * @return
     *     The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
