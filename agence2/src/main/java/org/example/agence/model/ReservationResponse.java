package org.example.agence.model;

public class ReservationResponse {
    private String id;
    private String reference;
    private double prixTotal;
    private int nombreNuits;
    private float dateDebut;
    private float dateFin;
    private ClientInfo client;
    private boolean success;
    private String error;

    public String getId() { return id; }
    public void setId(Long id) { this.id = id != null ? id.toString() : null; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
    public int getNombreNuits() { return nombreNuits; }
    public void setNombreNuits(int nombreNuits) { this.nombreNuits = nombreNuits; }
    public float getDateDebut() { return dateDebut; }
    public void setDateDebut(float dateDebut) { this.dateDebut = dateDebut; }
    public float getDateFin() { return dateFin; }
    public void setDateFin(float dateFin) { this.dateFin = dateFin; }
    public ClientInfo getClient() { return client; }
    public void setClient(ClientInfo client) { this.client = client; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }


}
