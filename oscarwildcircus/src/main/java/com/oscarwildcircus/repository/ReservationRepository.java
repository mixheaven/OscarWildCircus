package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
