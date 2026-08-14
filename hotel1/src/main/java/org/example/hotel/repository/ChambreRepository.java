package org.example.hotel.repository;

import org.example.hotel.model.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    @Query("SELECT DISTINCT c FROM Chambre c " +
            "WHERE c.disponible = true " +
            "AND c.nombreLits >= :nbPersonnes " +
            "AND UPPER(c.hotel.adresse.ville) = UPPER(:ville) " +
            "AND NOT EXISTS (" +
            "   SELECT r FROM Reservation r " +
            "   WHERE r.chambre = c " +
            "   AND r.dateDebut < :dateFin " +
            "   AND r.dateFin > :dateDebut" +
            ") " +
            "ORDER BY c.prix ASC")
    Optional<Chambre> findChambreDisponible(@Param("nbPersonnes") int nbPersonnes,
                                            @Param("ville") String ville,
                                            @Param("dateDebut") Date dateDebut,
                                            @Param("dateFin") Date dateFin);
}
