package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Activity;
import com.oscarwildcircus.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
