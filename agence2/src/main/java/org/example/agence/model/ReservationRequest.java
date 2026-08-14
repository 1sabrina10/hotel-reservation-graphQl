package org.example.agence.model;

import java.util.Date;

public class ReservationRequest {
    private Long agenceId;
    private Long chambreId;
    private String nomClient;
    private String prenomClient;
    private String email;
    private String numeroCarte;
    private String cvc;
    private double prixTotal;
    private int nombreNuits;
    private Date dateDebut;
    private Date dateFin;

    public ReservationRequest() {}

    public Long getAgenceId() { return agenceId; }
    public void setAgenceId(Long agenceId) { this.agenceId = agenceId; }

    public Long getChambreId() { return chambreId; }
    public void setChambreId(Long chambreId) { this.chambreId = chambreId; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getPrenomClient() { return prenomClient; }
    public void setPrenomClient(String prenomClient) { this.prenomClient = prenomClient; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumeroCarte() { return numeroCarte; }
    public void setNumeroCarte(String numeroCarte) { this.numeroCarte = numeroCarte; }

    public String getCvc() { return cvc; }
    public void setCvc(String cvc) { this.cvc = cvc; }

    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }

    public int getNombreNuits() { return nombreNuits; }
    public void setNombreNuits(int nombreNuits) { this.nombreNuits = nombreNuits; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
}
