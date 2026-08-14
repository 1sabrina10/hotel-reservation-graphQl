package org.example.comparateur.controller;

import org.example.comparateur.model.ChambreDTO;
import org.example.comparateur.model.OffreAgence;
import org.example.comparateur.service.ComparateurService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class ComparateurController {

    private final ComparateurService service;

    public ComparateurController(ComparateurService service) {
        this.service = service;
    }

    @GetMapping("/comparateur")
    public String pageComparateur() {
        return "comparateur";
    }

    @QueryMapping
    public List<OffreAgence> offres(@Argument int nbPersonnes,
                                    @Argument String ville,
                                    @Argument Float dateDebut,
                                    @Argument Float dateFin) {

        return service.getOffres(ville, nbPersonnes,
                new Date(dateDebut.longValue()),
                new Date(dateFin.longValue()));
    }

    @SchemaMapping(typeName = "ChambreDTO", field = "image")
    public String getImageBase64(ChambreDTO chambre) {
        if (chambre != null && chambre.getImage() != null && chambre.getImage().length > 0) {
            return Base64.getEncoder().encodeToString(chambre.getImage());
        }
        return null;
    }
}
