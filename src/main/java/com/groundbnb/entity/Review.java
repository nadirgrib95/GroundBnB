package com.groundbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    @OneToOne
    @JoinColumn(name = "reservation_id", unique = true)
    private Reservation reservation;

    public Review() {
    }

    public Review(Long id, Integer rating, String comment, Customer customer, Listing listing, Reservation reservation) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
        this.listing = listing;
        this.reservation = reservation;
    }

}
