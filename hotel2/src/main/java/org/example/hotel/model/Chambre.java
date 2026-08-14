package org.example.hotel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private int nombreLits;
    private double prix;
    private boolean disponible;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "chambre", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    public Chambre() {
    }

    public Chambre(String type, int nombreLits, double prix, boolean disponible, byte[] image) {
        this.type = type;
        this.nombreLits = nombreLits;
        this.prix = prix;
        this.disponible = disponible;
        this.image = image;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
