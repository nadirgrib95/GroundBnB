package com.groundbnb.controller;

import com.groundbnb.entity.Reservation;
import com.groundbnb.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @GetMapping("/customer/{id}")
    public List<Reservation> getByCustomer(@PathVariable Long id) {
        return reservationService.getByCustomerId(id);
    }
}
