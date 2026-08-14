package org.example.hotel.repository;

import org.example.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByIdAndAdresseVilleIgnoreCase(Long hotelId, String ville);
}
