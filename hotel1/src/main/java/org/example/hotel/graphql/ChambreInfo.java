package org.example.hotel.graphql;

public class ChambreInfo {
    private Long id;
    private String type;
    private int nombreLits;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getNombreLits() { return nombreLits; }
    public void setNombreLits(int nombreLits) { this.nombreLits = nombreLits; }
}

