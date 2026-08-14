package org.example.comparateur.model;


public class OffreAgence {

    private Long offreId;

    private Long agenceId;

    private ChambreDTO chambreDTO;

    public OffreAgence() {
    }

    public Long getOffreId() {
        return offreId;
    }

    public void setOffreId(Long offreId) {
        this.offreId = offreId;
    }

    public Long getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Long agenceId) {
        this.agenceId = agenceId;
    }

    public ChambreDTO getChambreDTO() {
        return chambreDTO;
    }

    public void setChambreDTO(ChambreDTO chambreDTO) {
        this.chambreDTO = chambreDTO;
    }
}
