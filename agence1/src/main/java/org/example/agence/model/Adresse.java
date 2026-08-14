package org.example.agence.model;

public class Adresse {
    private String pays;
    private String ville;
    private String rue;
    private String numero;
    private String GPS;

    public Adresse() {
    }

    public Adresse(String pays, String ville, String rue, String numero, String GPS) {
        this.pays = pays;
        this.ville = ville;
        this.rue = rue;
        this.numero = numero;
        this.GPS = GPS;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }
}


