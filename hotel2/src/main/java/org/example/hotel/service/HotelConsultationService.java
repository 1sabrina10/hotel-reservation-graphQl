
package org.example.hotel.service;

import org.example.hotel.dto.ChambreDTO;
import org.example.hotel.model.*;
import org.example.hotel.repository.AgenceRepository;
import org.example.hotel.repository.HotelAgenceRepository;
import org.example.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HotelConsultationService {

    private final AgenceRepository agenceRepository;
    private final HotelAgenceRepository hotelAgenceRepository;
    private final HotelRepository hotelRepository;

    public HotelConsultationService(AgenceRepository agenceRepository,
                                    HotelAgenceRepository hotelAgenceRepository,
                                    HotelRepository hotelRepository) {
        this.agenceRepository = agenceRepository;
        this.hotelAgenceRepository = hotelAgenceRepository;
        this.hotelRepository = hotelRepository;
    }

    public boolean authentifierAgence(String email, String password) {
        Optional<Agence> optAgence = agenceRepository.findFirstByEmailIgnoreCaseAndPassword(email, password);
        return optAgence.isPresent();
    }
    public List<ChambreDTO> consulterDisponibilites(Long hotelId, String email, String password, int nbPersonnes, String ville, Date dateDebut, Date dateFin) {
        List<ChambreDTO> result = new ArrayList<>();

        if (dateDebut == null || dateFin == null) {
            System.out.println("Dates de réservation invalides");
            return result;
        }

        Optional<Agence> optAgence = agenceRepository.findFirstByEmailIgnoreCaseAndPassword(email, password);
        if (!optAgence.isPresent()) {
            System.out.println("Authentification échouée pour l'agence : " + email);
            return result;
        }
        Agence agence = optAgence.get();

        Hotel hotel = hotelRepository.findByIdAndAdresseVilleIgnoreCase(hotelId, ville);
        if (hotel == null) {
            System.out.println("Aucun hôtel trouvé pour la ville : " + ville);
            return result;
        }


            HotelAgence ha = hotelAgenceRepository.findByHotelIdAndAgenceId(hotel.getId(), agence.getId());
            if (ha == null) return List.of();

            if (hotel.getChambres() == null) return List.of();

            for (Chambre chambre : hotel.getChambres()) {

                if (!chambre.isDisponible() || chambre.getNombreLits() < nbPersonnes) continue;


                if (!estDisponible(chambre, dateDebut, dateFin)) continue;


                ChambreDTO dto = new ChambreDTO(
                        chambre.getId(),
                        hotel.getNom(),
                        hotel.getAdresse(),
                        chambre.getType(),
                        chambre.getNombreLits(),
                        chambre.getPrix(),
                        chambre.getImage()
                );

                result.add(dto);
            }


        System.out.println("Nombre de chambres disponibles : " + result.size());
        return result;
    }


    public boolean estDisponible(Chambre chambre, Date dateDebut, Date dateFin) {
        if (chambre.getReservations() == null || chambre.getReservations().isEmpty()) return true;

        for (Reservation reservation : chambre.getReservations()) {
            Date resDebut = reservation.getDateDebut();
            Date resFin = reservation.getDateFin();


            if (resDebut == null || resFin == null) continue;

            if (datesChevauchent(dateDebut, dateFin, resDebut, resFin)) {
                return false;
            }
        }
        return true;
    }


    private boolean datesChevauchent(Date debut1, Date fin1, Date debut2, Date fin2) {
        if (debut1 == null || fin1 == null || debut2 == null || fin2 == null) {
            return false;
        }
        return !debut1.after(fin2) && !fin1.before(debut2);
    }

}

