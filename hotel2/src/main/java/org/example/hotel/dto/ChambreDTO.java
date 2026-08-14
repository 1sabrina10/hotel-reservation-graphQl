package org.example.hotel.dto;

import org.example.hotel.model.Adresse;
import java.io.Serializable;

public class ChambreDTO implements Serializable {

    private Long id;
    private String nomHotel;
    private Adresse adresse;
    private String type;
    private int nombreLits;
    private double prix;
    private byte[] image;

    public ChambreDTO(Long id, String nomHotel, Adresse adresse, String type,
                      int nombreLits, double prix,
                      byte[] image) {

        this.id = id;
        this.nomHotel = nomHotel;
        this.adresse = adresse;
        this.type = type;
        this.nombreLits = nombreLits;
        this.prix = prix;
        this.image = image;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNombreLits() {
        return nombreLits;
    }

    public void setNombreLits(int nombreLits) {
        this.nombreLits = nombreLits;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
