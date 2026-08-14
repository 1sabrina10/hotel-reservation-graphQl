package org.example.agence.model;

public class ReservationInput {
    private String agenceId;
    private String chambreId;
    private String nomClient;
    private String prenomClient;
    private String email;
    private String numeroCarte;
    private String cvc;
    private double prixTotal;
    private int nombreNuits;
    private String dateDebut;
    private String dateFin;

    public String getAgenceId() { return agenceId; }
    public void setAgenceId(String agenceId) { this.agenceId = agenceId; }
    public String getChambreId() { return chambreId; }
    public void setChambreId(String chambreId) { this.chambreId = chambreId; }
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
    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
}
