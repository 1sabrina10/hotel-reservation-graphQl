package org.example.hotel.service;

import org.example.hotel.model.*;
import org.example.hotel.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final AgenceRepository agenceRepository;
    private final ChambreRepository chambreRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              ClientRepository clientRepository,
                              AgenceRepository agenceRepository,
                              ChambreRepository chambreRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.agenceRepository = agenceRepository;
        this.chambreRepository = chambreRepository;
    }

    @Transactional
    public Reservation reserverChambreAuto(Long agenceId,
                                           String nomClient,
                                           String prenomClient,
                                           Date dateDebut,
                                           Date dateFin,
                                           String email,
                                           String numeroCarte,
                                           String cvc,
                                           double prixTotal,
                                           int nombreNuits,
                                           int nbPersonnes,
                                           String ville) {


        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new RuntimeException("Agence introuvable"));


        Chambre chambre = chambreRepository.findChambreDisponible(nbPersonnes, ville, dateDebut, dateFin)
                .orElseThrow(() -> new RuntimeException("Aucune chambre disponible"));


        Client client = clientRepository.findByNomIgnoreCaseAndPrenomIgnoreCase(nomClient, prenomClient);
        if (client == null) {
            client = new Client();
            client.setNom(nomClient);
            client.setPrenom(prenomClient);
            client.setEmail(email);
            client.setNumeroCarte(numeroCarte);
            client.setCvc(cvc);
            clientRepository.save(client);
        }


        Reservation reservation = new Reservation();
        reservation.setChambre(chambre);
        reservation.setClient(client);
        reservation.setAgence(agence);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setPrixTotal(prixTotal);
        reservation.setNombreNuits(nombreNuits);
        reservation.setReference(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        return reservationRepository.save(reservation);
    }

    @Transactional
    public boolean annulerReservation(String email, String reference) {
        return reservationRepository.findByClientEmailAndReference(email, reference)
                .map(res -> { reservationRepository.delete(res); return true; })
                .orElse(false);
    }
}
