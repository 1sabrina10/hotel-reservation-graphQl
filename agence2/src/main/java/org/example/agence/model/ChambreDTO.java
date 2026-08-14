//package org.example.agence.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
//public class ChambreDTO implements Serializable {
//
//    private Long id;
//    private String nomHotel;
//    private Adresse adresse;
//    private String type;
//    private int nombreLits;
//    private double prix;
//    private double reduction;
//    private byte[] image;
//    private boolean hasOffer;
//    private int etoiles;
//    private Date dateDebut;
//    private Date dateFin;
//    public ChambreDTO(Long id, String nomHotel, Adresse adresse, String type,
//                      int nombreLits, double prix, double reduction,
//                      byte[] image, boolean hasOffer, int etoiles) {
//
//        this.id = id;
//        this.nomHotel = nomHotel;
//        this.adresse = adresse;
//        this.type = type;
//        this.nombreLits = nombreLits;
//        this.prix = prix;
//        this.reduction = reduction;
//        this.image = image;
//        this.hasOffer = hasOffer;
//        this.etoiles = etoiles;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNomHotel() {
//        return nomHotel;
//    }
//
//    public void setNomHotel(String nomHotel) {
//        this.nomHotel = nomHotel;
//    }
//
//    public Adresse getAdresse() {
//        return adresse;
//    }
//
//    public void setAdresse(Adresse adresse) {
//        this.adresse = adresse;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public int getNombreLits() {
//        return nombreLits;
//    }
//
//    public void setNombreLits(int nombreLits) {
//        this.nombreLits = nombreLits;
//    }
//
//    public double getPrix() {
//        return prix;
//    }
//
//    public void setPrix(double prix) {
//        this.prix = prix;
//    }
//
//    public double getReduction() {
//        return reduction;
//    }
//
//    public void setReduction(double reduction) {
//        this.reduction = reduction;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public boolean isHasOffer() {
//        return hasOffer;
//    }
//
//    public void setHasOffer(boolean hasOffer) {
//        this.hasOffer = hasOffer;
//    }
//
//    public int getEtoiles() {
//        return etoiles;
//    }
//
//    public void setEtoiles(int etoiles) {
//        this.etoiles = etoiles;
//    }
//
//    public Date getDateDebut() {
//        return dateDebut;
//    }
//
//    public void setDateDebut(Date dateDebut) {
//        this.dateDebut = dateDebut;
//    }
//
//    public Date getDateFin() {
//        return dateFin;
//    }
//
//    public void setDateFin(Date dateFin) {
//        this.dateFin = dateFin;
//    }
//}
package org.example.agence.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

public class ChambreDTO implements Serializable {

    private Long id;
    private String nomHotel;
    private Adresse adresse;
    private String type;
    private int nombreLits;
    private double prix;
    private double reduction;
    private byte[] image;
    private boolean hasOffer;
    private int etoiles;
    private Date dateDebut;
    private Date dateFin;
    private int nombreNuits;
    private double prixTotal;


    public ChambreDTO() {
    }

    public ChambreDTO(Long id, String nomHotel, Adresse adresse, String type,
                      int nombreLits, double prix, double reduction,
                      byte[] image, boolean hasOffer, int etoiles) {

        this.id = id;
        this.nomHotel = nomHotel;
        this.adresse = adresse;
        this.type = type;
        this.nombreLits = nombreLits;
        this.prix = prix;
        this.reduction = reduction;
        this.image = image;
        this.hasOffer = hasOffer;
        this.etoiles = etoiles;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomHotel() { return nomHotel; }
    public void setNomHotel(String nomHotel) { this.nomHotel = nomHotel; }

    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getNombreLits() { return nombreLits; }
    public void setNombreLits(int nombreLits) { this.nombreLits = nombreLits; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public double getReduction() { return reduction; }
    public void setReduction(double reduction) { this.reduction = reduction; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public void setImage(String imageBase64) {
        if (imageBase64 != null && !imageBase64.isEmpty()) {
            try {
                this.image = Base64.getDecoder().decode(imageBase64);
            } catch (IllegalArgumentException e) {
                System.err.println("[WARNING] Erreur lors du décodage base64 de l'image: " + e.getMessage());
                this.image = null;
            }
        } else {
            this.image = null;
        }
    }

    public boolean isHasOffer() { return hasOffer; }
    public void setHasOffer(boolean hasOffer) { this.hasOffer = hasOffer; }

    public int getEtoiles() { return etoiles; }
    public void setEtoiles(int etoiles) { this.etoiles = etoiles; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }


    public int getNombreNuits() { return nombreNuits; }
    public void setNombreNuits(int nombreNuits) { this.nombreNuits = nombreNuits; }

    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
}
