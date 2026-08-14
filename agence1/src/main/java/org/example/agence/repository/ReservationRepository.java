package org.example.agence.repository;

import org.example.agence.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByClientEmailAndReference(String email, String reference);

    Optional<Reservation> findByReference(String reference);
    Reservation findTopByClientEmailOrderByIdDesc(String email);
}
