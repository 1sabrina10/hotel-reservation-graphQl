package org.example.hotel.repository;

import org.example.hotel.model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

    Optional<Agence> findFirstByEmailIgnoreCaseAndPassword(String email, String password);
}
