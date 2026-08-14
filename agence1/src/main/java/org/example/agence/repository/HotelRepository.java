package org.example.agence.repository;

import org.example.agence.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByAdresseVilleIgnoreCase(String ville);
    Optional<Hotel> findFirstByNom(String nom);
}

