package com.aiToolFinder.aiToolFinder.entity;


import jakarta.persistence.*;


@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long toolId;
    private int userRating;
    private String comment;
    private String status;

    public Review() {
    }

    public Review(Long id, Long toolId, int userRating, String comment, String status) {
        this.id = id;
        this.toolId = toolId;
        this.userRating = userRating;
        this.comment = comment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", toolId=" + toolId +
                ", userRating=" + userRating +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

