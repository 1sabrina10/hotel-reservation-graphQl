package org.example.agence.controller;

import org.example.agence.model.OffreAgence;
import org.example.agence.service.AgenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AgenceService agenceService;

    public LoginController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }


    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) Long agenceId, @RequestParam(required = false) Long offreId, Model model) {
        model.addAttribute("agenceId", agenceId != null ? agenceId : 0);
        model.addAttribute("offreId", offreId != null ? offreId : 0);
        return "login";
    }


    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email,
                              @RequestParam String password,
                              @RequestParam(required = false, defaultValue = "0") Long agenceId,
                              @RequestParam(required = false, defaultValue = "0") Long offreId,
                              Model model) {

        boolean auth = agenceService.authentifierAgence(email, password);

        if (auth) {
            if (offreId != null && offreId > 0 && agenceId != null && agenceId > 0) {
                OffreAgence offre = agenceService.getOffreById(agenceId, offreId);
                if (offre != null) {
                    model.addAttribute("email", email);
                    model.addAttribute("password", password);
                    model.addAttribute("offreId", offreId);
                    model.addAttribute("agenceId", agenceId);
                    model.addAttribute("offre", offre);
                    return "apercu";
                }
            }

            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("agenceId", agenceId != null && agenceId > 0 ? agenceId : 2);
            return "reservations";
        } else {
            model.addAttribute("agenceId", agenceId != null && agenceId > 0 ? agenceId : 0);
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login";
        }
    }
}

