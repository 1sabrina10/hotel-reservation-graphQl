package org.example.agence.controller;

import org.example.agence.model.*;
import org.example.agence.service.AgenceService;
import org.example.agence.service.PdfService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
public class AgenceController {

    private final AgenceService service;
    private final PdfService pdfService;

    public AgenceController(AgenceService service, PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @QueryMapping
    public Map<String, Boolean> testAuth(@Argument String email, @Argument String password) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("auth", service.authentifierAgence(email, password));
        return map;
    }

    @QueryMapping
    public List<OffreAgence> offres(@Argument String email,
                                    @Argument String password,
                                    @Argument int nbPersonnes,
                                    @Argument String ville,
                                    @Argument long dateDebut,
                                    @Argument long dateFin,
                                    @Argument String agenceId) {
        Long agenceIdLong = null;
        if (agenceId != null && !agenceId.isEmpty()) {
            agenceIdLong = Long.parseLong(agenceId);
        }
        return service.consulterOffres(email, password, nbPersonnes, ville,
                new Date(dateDebut), new Date(dateFin), agenceIdLong);
    }

    @QueryMapping
    public OffreAgence offreById(@Argument String agenceId, @Argument String offreId) {
        return service.getOffreById(Long.parseLong(agenceId), Long.parseLong(offreId));
    }

    @MutationMapping
    public ReservationResponse reserver(@Argument("input") ReservationInput input) {
        try {
            ReservationRequest request = new ReservationRequest();
            request.setAgenceId(Long.parseLong(input.getAgenceId()));
            request.setChambreId(Long.parseLong(input.getChambreId()));
            request.setNomClient(input.getNomClient());
            request.setPrenomClient(input.getPrenomClient());
            request.setEmail(input.getEmail());
            request.setNumeroCarte(input.getNumeroCarte());
            request.setCvc(input.getCvc());
            request.setPrixTotal(input.getPrixTotal());
            request.setNombreNuits(input.getNombreNuits());
            request.setDateDebut(new Date(Long.parseLong(input.getDateDebut())));
            request.setDateFin(new Date(Long.parseLong(input.getDateFin())));


            Reservation savedReservation = service.reserverChambre(request);

            if (savedReservation != null) {
                ReservationResponse response = new ReservationResponse();
                response.setId(savedReservation.getId());
                response.setReference(savedReservation.getReference());
                response.setPrixTotal(savedReservation.getPrixTotal());
                response.setNombreNuits(savedReservation.getNombreNuits());
                response.setDateDebut(savedReservation.getDateDebut().getTime());
                response.setDateFin(savedReservation.getDateFin().getTime());

                ClientInfo clientInfo = new ClientInfo();
                clientInfo.setNom(savedReservation.getClient().getNom());
                clientInfo.setPrenom(savedReservation.getClient().getPrenom());
                clientInfo.setEmail(savedReservation.getClient().getEmail());
                response.setClient(clientInfo);

                response.setSuccess(true);
                return response;
            } else {
                ReservationResponse response = new ReservationResponse();
                response.setSuccess(false);
                response.setError("Erreur lors de la réservation.");
                return response;
            }
        } catch (Exception e) {
            ReservationResponse response = new ReservationResponse();
            response.setSuccess(false);
            response.setError("Erreur lors du traitement : " + e.getMessage());
            return response;
        }
    }


    @SchemaMapping(typeName = "ChambreDTO", field = "image")
    public String getImageBase64(ChambreDTO chambre) {
        if (chambre.getImage() != null && chambre.getImage().length > 0) {
            return Base64.getEncoder().encodeToString(chambre.getImage());
        }
        return null;
    }

    @SchemaMapping(typeName = "ChambreDTO", field = "dateDebut")
    public Float getDateDebutLong(ChambreDTO chambre) {
        return chambre.getDateDebut() != null ? (float) chambre.getDateDebut().getTime() : null;
    }

    @SchemaMapping(typeName = "ChambreDTO", field = "dateFin")
    public Float getDateFinLong(ChambreDTO chambre) {
        return chambre.getDateFin() != null ? (float) chambre.getDateFin().getTime() : null;
    }

    @SchemaMapping(typeName = "Adresse", field = "gps")
    public String getGps(Adresse adresse) {
        return adresse.getGPS();
    }

}
