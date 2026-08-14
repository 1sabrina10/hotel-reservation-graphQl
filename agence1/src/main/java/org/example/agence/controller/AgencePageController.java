package org.example.agence.controller;

import org.example.agence.model.Reservation;
import org.example.agence.model.ReservationRequest;
import org.example.agence.service.AgenceService;
import org.example.agence.service.PdfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/agence")
public class AgencePageController {

    private final AgenceService service;
    private final PdfService pdfService;

    public AgencePageController(AgenceService service, PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @PostMapping("/reserver")
    public ModelAndView reserverChambre(@RequestParam Long agenceId,
                                        @RequestParam Long chambreId,
                                        @RequestParam String nomClient,
                                        @RequestParam String prenomClient,
                                        @RequestParam String email,
                                        @RequestParam String numeroCarte,
                                        @RequestParam String cvc,
                                        @RequestParam double prixTotal,
                                        @RequestParam int nombreNuits,
                                        @RequestParam String dateDebut,
                                        @RequestParam String dateFin) {
        ModelAndView mv = new ModelAndView();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebutDate = sdf.parse(dateDebut);
            Date dateFinDate = sdf.parse(dateFin);
            
            ReservationRequest request = new ReservationRequest();
            request.setAgenceId(agenceId);
            request.setChambreId(chambreId);
            request.setNomClient(nomClient);
            request.setPrenomClient(prenomClient);
            request.setEmail(email);
            request.setNumeroCarte(numeroCarte);
            request.setCvc(cvc);
            request.setPrixTotal(prixTotal);
            request.setNombreNuits(nombreNuits);
            request.setDateDebut(dateDebutDate);
            request.setDateFin(dateFinDate);
            
            Reservation reservation = service.reserverChambre(request);
            
            if (reservation != null) {

                pdfService.genererPdfReservation(reservation);
                
                mv.setViewName("confirmation");
                mv.addObject("reference", reservation.getReference());
                mv.addObject("reservation", reservation);
            } else {
                mv.setViewName("apercu");
                mv.addObject("error", "Erreur lors de la réservation. Veuillez réessayer.");
            }
        } catch (Exception e) {
            mv.setViewName("apercu");
            mv.addObject("error", "Erreur lors de la réservation: " + e.getMessage());
        }
        
        return mv;
    }

    @GetMapping("/reservations")
    public String mesReservations() {
        return "reservations";
    }

    @GetMapping("/reservations/rechercher")
    public ModelAndView rechercherReservation(@RequestParam String reference) {
        ModelAndView mv = new ModelAndView("reservations");
        Reservation reservation = service.getReservationByReference(reference);
        if (reservation == null) {
            mv.addObject("error", "Référence introuvable");
        } else {
            mv.addObject("reservation", reservation);
        }
        return mv;
    }

    @PostMapping("/reservations/annuler")
    public ModelAndView annulerReservation(@RequestParam String reference) {
        ModelAndView mv = new ModelAndView("reservations");
        boolean ok = service.annulerReservation(reference);
        if (ok) {
            mv.addObject("success", "Réservation annulée avec succès !");
        } else {
            mv.addObject("error", "Référence introuvable");
        }
        return mv;
    }
}


