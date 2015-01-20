
package com.david.socialhere.models.youtube;

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

    public String getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(String autoPlay) {
        this.autoPlay = autoPlay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentVote() {
        return commentVote;
    }

    public void setCommentVote(String commentVote) {
        this.commentVote = commentVote;
    }

    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSyndicate() {
        return syndicate;
    }

    public void setSyndicate(String syndicate) {
        this.syndicate = syndicate;
    }

    public String getVideoRespond() {
        return videoRespond;
    }

    public void setVideoRespond(String videoRespond) {
        this.videoRespond = videoRespond;
    }

}
