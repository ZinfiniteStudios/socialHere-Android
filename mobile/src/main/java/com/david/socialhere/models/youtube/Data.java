
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @Expose
    private String author;
    @Expose
    private Content content;
    @Expose
    private String description;
    @Expose
    private String id;
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @Expose
    private Integer itemsPerPage;
    @Expose
    private Integer startIndex;
    @Expose
    private Thumbnail_ thumbnail;
    @Expose
    private String title;
    @Expose
    private Integer totalItems;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Thumbnail_ getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail_ thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
