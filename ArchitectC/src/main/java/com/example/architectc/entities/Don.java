
package com.example.architectc.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campagne_id", nullable = false)
    private Campagne campagne;

    @Column(nullable = false)
    private String nomDonateur;

    @Column(nullable = false)
    private BigDecimal montant;

    @Column(nullable = false)
    private LocalDateTime date;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campagne getCampagne() {
        return campagne;
    }

    public void setCampagne(Campagne campagne) {
        this.campagne = campagne;
    }

    public String getNomDonateur() {
        return nomDonateur;
    }

    public void setNomDonateur(String nomDonateur) {
        this.nomDonateur = nomDonateur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {  // Changed from Double to BigDecimal
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}