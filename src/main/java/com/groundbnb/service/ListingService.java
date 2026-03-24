package com.groundbnb.service;

import com.groundbnb.entity.Listing;
import com.groundbnb.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService (ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    public List<Listing> getByCity(String city) {
        return listingRepository.findByCity(city);
    }

    public Listing create(Listing listing) {
        return listingRepository.save(listing);
    }
}
