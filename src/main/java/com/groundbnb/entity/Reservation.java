package com.groundbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private Integer guestCount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    @OneToOne(mappedBy = "reservation")
    private Review review;

    public Reservation() {
    }

    public Reservation(Long id, LocalDate checkInDate, LocalDate checkOutDate, Integer guestCount, BigDecimal totalPrice, Customer customer, Listing listing, Review review) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestCount = guestCount;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.listing = listing;
        this.review = review;
    }

    public boolean isActive() {
        return checkInDate.isBefore(LocalDate.now()) && checkOutDate.isAfter(LocalDate.now());
    }
}
