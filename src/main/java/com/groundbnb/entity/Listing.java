package com.groundbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "listing")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false, updatable = false, length = 75)
    private String publicId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 150)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(name = "main_image_url", nullable = false, length = 500)
    private String mainImageUrl;

    @Column(name = "avg_rating")
    private Double avgRating;

    @Column(name = "review_count")
    private Integer reviewCount;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public Listing(String title, String description, BigDecimal price, String address,
                   String city, String state, String zipCode, String country, String mainImageUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.mainImageUrl = mainImageUrl;
        this.publicId = generatePublicId();
        this.reviewCount = 0;
        this.avgRating = 0.0;
    }

    private String generatePublicId() {
        return "lst-" + UUID.randomUUID();
    }

    // Helper methods
    public String getFullAddress() {
        return String.format("%s, %s, %s %s, %s",
                address, city, state, zipCode, country);
    }

    public String getShortAddress() {
        return String.format("%s, %s", city, country);
    }

    public void updateRating() {
        if (reviews != null && !reviews.isEmpty()) {
            double sum = reviews.stream()
                    .mapToDouble(Review::getRating)
                    .sum();
            this.avgRating = sum / reviews.size();
            this.reviewCount = reviews.size();
        } else {
            this.avgRating = 0.0;
            this.reviewCount = 0;
        }
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new java.util.ArrayList<>();
        }
        reviews.add(review);
        updateRating();
    }

    public void removeReview(Review review) {
        if (reviews != null) {
            reviews.remove(review);
            updateRating();
        }
    }

    public boolean isAvailable() {
        if (reservations == null || reservations.isEmpty()) {
            return true;
        }

        return reservations.stream().noneMatch(Reservation::isActive);
    }

    public BigDecimal calculateTotalPrice(int nights) {
        return price.multiply(BigDecimal.valueOf(nights));
    }

    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }

    public String getFormattedRating() {
        if (reviewCount == null || reviewCount == 0) {
            return "No reviews yet";
        }
        return String.format("%.1f (%d reviews)", avgRating, reviewCount);
    }
}