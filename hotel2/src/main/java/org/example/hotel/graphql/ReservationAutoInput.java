package org.example.hotel.graphql;

public class ReservationAutoInput {
    private Long agenceId;
    private String nomClient;
    private String prenomClient;
    private String email;
    private String numeroCarte;
    private String cvc;
    private double prixTotal;
    private int nombreNuits;
    private int nbPersonnes;
    private String ville;
    private long dateDebut;
    private long dateFin;

    public Long getAgenceId() { return agenceId; }
    public void setAgenceId(Long agenceId) { this.agenceId = agenceId; }
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
    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int nbPersonnes) { this.nbPersonnes = nbPersonnes; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public long getDateDebut() { return dateDebut; }
    public void setDateDebut(long dateDebut) { this.dateDebut = dateDebut; }
    public long getDateFin() { return dateFin; }
    public void setDateFin(long dateFin) { this.dateFin = dateFin; }
}
