package org.example.agence.repository;

import org.example.agence.model.HotelAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelAgenceRepository extends JpaRepository<HotelAgence, Long> {
    @Query("SELECT ha FROM HotelAgence ha WHERE ha.hotel.id = :hotelId AND ha.agence.id = :agenceId")
    HotelAgence findByHotelIdAndAgenceId(@Param("hotelId") Long hotelId, @Param("agenceId") Long agenceId);
    
    @Query("SELECT ha FROM HotelAgence ha WHERE ha.agence.id = :agenceId")
    List<HotelAgence> findByAgenceId(@Param("agenceId") Long agenceId);
}
