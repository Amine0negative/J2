package com.example.architectc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotNull
    @Positive
    private BigDecimal objectifMontant;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    public String getNom() {
        return this.titre;
    }
}