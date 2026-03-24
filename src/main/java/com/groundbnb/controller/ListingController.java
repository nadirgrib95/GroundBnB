package com.groundbnb.controller;

import com.groundbnb.entity.Listing;
import com.groundbnb.service.ListingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<Listing> getAll() {
        return listingService.getAll();
    }

    @GetMapping("/city/{city}")
    public List<Listing> getByCity(@PathVariable String city) {
        return listingService.getByCity(city);
    }

    @PostMapping
    public Listing create(@RequestBody Listing listing) {
        return listingService.create(listing);
    }
}
