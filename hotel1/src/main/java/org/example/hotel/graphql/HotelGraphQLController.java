package org.example.hotel.graphql;

import org.example.hotel.dto.ChambreDTO;
import org.example.hotel.model.*;
import org.example.hotel.service.HotelConsultationService;
import org.example.hotel.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class HotelGraphQLController {

    private final HotelConsultationService consultationService;
    private final ReservationService reservationService;

    public HotelGraphQLController(HotelConsultationService consultationService,
                                  ReservationService reservationService) {
        this.consultationService = consultationService;
        this.reservationService = reservationService;
    }

    @QueryMapping
    public boolean auth(@Argument String email, @Argument String password) {
        return consultationService.authentifierAgence(email, password);
    }

    @QueryMapping
    public List<ChambreDTO> disponibilites(@Argument Long hotelId,
                                           @Argument String email,
                                           @Argument String password,
                                           @Argument int nbPersonnes,
                                           @Argument String ville,
                                           @Argument long dateDebut,
                                           @Argument long dateFin) {
        return consultationService.consulterDisponibilites(
                hotelId, email, password, nbPersonnes, ville,
                new Date(dateDebut), new Date(dateFin)
        );
    }

    @MutationMapping
    public Reservation reserverAuto(@Argument ReservationAutoInput input) {
        return reservationService.reserverChambreAuto(
                input.getAgenceId(),
                input.getNomClient(),
                input.getPrenomClient(),
                new Date(input.getDateDebut()),
                new Date(input.getDateFin()),
                input.getEmail(),
                input.getNumeroCarte(),
                input.getCvc(),
                input.getPrixTotal(),
                input.getNombreNuits(),
                input.getNbPersonnes(),
                input.getVille()
        );
    }

    @MutationMapping
    public boolean annuler(@Argument String email, @Argument String reference) {
        return reservationService.annulerReservation(email, reference);
    }


    @SchemaMapping(typeName = "ChambreDTO", field = "image")
    public String getImageBase64(ChambreDTO chambre) {
        if (chambre.getImage() != null && chambre.getImage().length > 0) {
            return Base64.getEncoder().encodeToString(chambre.getImage());
        }
        return null;
    }

    @SchemaMapping(typeName = "Adresse", field = "gps")
    public String getGps(Adresse adresse) {
        return adresse.getGPS();
    }

    @SchemaMapping(typeName = "Reservation", field = "dateDebut")
    public Long getDateDebutLong(Reservation reservation) {
        return reservation.getDateDebut() != null ? reservation.getDateDebut().getTime() : null;
    }

    @SchemaMapping(typeName = "Reservation", field = "dateFin")
    public Long getDateFinLong(Reservation reservation) {
        return reservation.getDateFin() != null ? reservation.getDateFin().getTime() : null;
    }

    @SchemaMapping(typeName = "Reservation", field = "client")
    public ClientInfo getClientInfo(Reservation reservation) {
        Client client = reservation.getClient();
        ClientInfo info = new ClientInfo();
        info.setNom(client.getNom());
        info.setPrenom(client.getPrenom());
        info.setEmail(client.getEmail());
        return info;
    }

    @SchemaMapping(typeName = "Reservation", field = "chambre")
    public ChambreInfo getChambreInfo(Reservation reservation) {
        Chambre chambre = reservation.getChambre();
        ChambreInfo info = new ChambreInfo();
        info.setId(chambre.getId());
        info.setType(chambre.getType());
        info.setNombreLits(chambre.getNombreLits());
        return info;
    }

}
