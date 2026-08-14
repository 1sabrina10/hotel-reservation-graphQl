package org.example.comparateur.model;


public class Adresse {
    private String pays;
    private String ville;
    private String rue;
    private String numero;
    private String gps;

    public Adresse() {}

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getGps() { return gps; }
    public void setGps(String gps) { this.gps = gps; }
}
