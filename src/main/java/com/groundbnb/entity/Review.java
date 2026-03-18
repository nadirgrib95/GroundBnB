package com.groundbnb.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double rating;
    private String comment;
    private long listingId;
    private long customerId;
    private long reservationId;


    public Review() {
    }

    public Review(long id, double rating, String comment, long listingId, long customerId, long reservationId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.listingId = listingId;
        this.customerId = customerId;
        this.reservationId = reservationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
