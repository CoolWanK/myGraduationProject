package com.wjk.demo.pojo;

import java.util.Date;
import java.util.List;

public class Comments {
    private Integer id;
    private Integer parentCommentId;
    private Integer business_id;
    private Integer user_id;
    private Date commentTime;
    private String content;
    private Comments parentComment;
    private List<Comments>replyCommentList;
    private User user;
    private User parentUser;
    private Integer isBusiness;

    public Integer getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User parentUser) {
        this.parentUser = parentUser;
    }

    public Comments getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comments parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comments> getReplyCommentList() {
        return replyCommentList;
    }

    public void setReplyCommentList(List<Comments> replyCommentList) {
        this.replyCommentList = replyCommentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", parentCommentId=" + parentCommentId +
                ", business_id=" + business_id +
                ", user_id=" + user_id +
                ", commentTime=" + commentTime +
                ", content='" + content + '\'' +
                ", parentComment=" + parentComment +
                ", replyCommentList=" + replyCommentList +
                '}';
    }
}
