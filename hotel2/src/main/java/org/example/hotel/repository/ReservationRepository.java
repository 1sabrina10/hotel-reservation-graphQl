package org.example.hotel.repository;

import org.example.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByClientEmailAndReference(String email, String reference);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.chambre.id = :chambreId " +
            "AND r.dateDebut <= :dateFin " +
            "AND r.dateFin >= :dateDebut")
    boolean existeChevauchement(
            @Param("chambreId") Long chambreId,
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin
    );
}

