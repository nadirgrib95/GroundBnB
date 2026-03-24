package com.groundbnb.service;

import com.groundbnb.entity.Reservation;
import com.groundbnb.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService (ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getByCustomerId(Long customerId) {
        return reservationRepository.findByCustomerId(customerId);
    }
}
