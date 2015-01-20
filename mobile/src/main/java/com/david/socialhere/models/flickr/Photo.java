
package com.david.socialhere.models.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @Expose
    private Integer farm;
    @SerializedName("height_l")
    @Expose
    private String heightL;
    @Expose
    private String id;
    @Expose
    private Integer isfamily;
    @Expose
    private Integer isfriend;
    @Expose
    private Integer ispublic;
    @Expose
    private String owner;
    @Expose
    private String ownername;
    @Expose
    private String secret;
    @Expose
    private String server;
    @Expose
    private String title;
    @SerializedName("url_l")
    @Expose
    private String urlL;
    @SerializedName("width_l")
    @Expose
    private String widthL;

    /**
     * 
     * @return
     *     The farm
     */
    public Integer getFarm() {
        return farm;
    }

    /**
     * 
     * @param farm
     *     The farm
     */
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * 
     * @return
     *     The heightL
     */
    public String getHeightL() {
        return heightL;
    }

    /**
     * 
     * @param heightL
     *     The height_l
     */
    public void setHeightL(String heightL) {
        this.heightL = heightL;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The isfamily
     */
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * 
     * @param isfamily
     *     The isfamily
     */
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    /**
     * 
     * @return
     *     The isfriend
     */
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * 
     * @param isfriend
     *     The isfriend
     */
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * 
     * @return
     *     The ispublic
     */
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * 
     * @param ispublic
     *     The ispublic
     */
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * 
     * @return
     *     The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The ownername
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * 
     * @param ownername
     *     The ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     * 
     * @return
     *     The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 
     * @param secret
     *     The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 
     * @return
     *     The server
     */
    public String getServer() {
        return server;
    }

    /**
     * 
     * @param server
     *     The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The urlL
     */
    public String getUrlL() {
        return urlL;
    }

    /**
     * 
     * @param urlL
     *     The url_l
     */
    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    /**
     * 
     * @return
     *     The widthL
     */
    public String getWidthL() {
        return widthL;
    }

    /**
     * 
     * @param widthL
     *     The width_l
     */
    public void setWidthL(String widthL) {
        this.widthL = widthL;
    }

}
