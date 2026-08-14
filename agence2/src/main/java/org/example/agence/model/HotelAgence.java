package org.example.agence.model;

import javax.persistence.*;

@Entity
@Table(name = "hotel_agence")
public class HotelAgence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    private double reduction;

    public HotelAgence() {}

    public HotelAgence(Hotel hotel, Agence agence, double reduction) {
        this.hotel = hotel;
        this.agence = agence;
        this.reduction = reduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }
    
    public Long getHotelId() {
        return hotel != null ? hotel.getId() : null;
    }
    
    public Long getAgenceId() {
        return agence != null ? agence.getId() : null;
    }

}


