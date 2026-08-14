package org.example.hotel.repository;

import org.example.hotel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);
}
