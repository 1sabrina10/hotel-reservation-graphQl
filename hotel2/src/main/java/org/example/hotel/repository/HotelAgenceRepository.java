package org.example.hotel.repository;

import org.example.hotel.model.HotelAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelAgenceRepository extends JpaRepository<HotelAgence, Long> {
    HotelAgence findByHotelIdAndAgenceId(Long hotelId, Long agenceId);
}
