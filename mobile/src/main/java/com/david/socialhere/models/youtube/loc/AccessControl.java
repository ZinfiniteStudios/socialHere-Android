
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;

public class AccessControl {

    @Expose
    private String autoPlay;
    @Expose
    private String comment;
    @Expose
    private String commentVote;
    @Expose
    private String embed;
    @Expose
    private String list;
    @Expose
    private String rate;
    @Expose
    private String syndicate;
    @Expose
    private String videoRespond;

    /**
     * 
     * @return
     *     The autoPlay
     */
    public String getAutoPlay() {
        return autoPlay;
    }

    /**
     * 
     * @param autoPlay
     *     The autoPlay
     */
    public void setAutoPlay(String autoPlay) {
        this.autoPlay = autoPlay;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 
     * @return
     *     The commentVote
     */
    public String getCommentVote() {
        return commentVote;
    }

    /**
     * 
     * @param commentVote
     *     The commentVote
     */
    public void setCommentVote(String commentVote) {
        this.commentVote = commentVote;
    }

    /**
     * 
     * @return
     *     The embed
     */
    public String getEmbed() {
        return embed;
    }

    /**
     * 
     * @param embed
     *     The embed
     */
    public void setEmbed(String embed) {
        this.embed = embed;
    }

    /**
     * 
     * @return
     *     The list
     */
    public String getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(String list) {
        this.list = list;
    }

    /**
     * 
     * @return
     *     The rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * 
     * @param rate
     *     The rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 
     * @return
     *     The syndicate
     */
    public String getSyndicate() {
        return syndicate;
    }

    /**
     * 
     * @param syndicate
     *     The syndicate
     */
    public void setSyndicate(String syndicate) {
        this.syndicate = syndicate;
    }

    /**
     * 
     * @return
     *     The videoRespond
     */
    public String getVideoRespond() {
        return videoRespond;
    }

    /**
     * 
     * @param videoRespond
     *     The videoRespond
     */
    public void setVideoRespond(String videoRespond) {
        this.videoRespond = videoRespond;
    }

}
