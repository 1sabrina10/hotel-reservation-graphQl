package org.example.agence.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String numeroCarte;
    private String cvc;

    public Client() {}

    public Client(String nom, String prenom, String email, String numeroCarte, String cvc) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroCarte = numeroCarte;
        this.cvc = cvc;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }
    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte; }


    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}

