package com.example.architectc.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonDTO {
    private Long id;
    
    @NotBlank(message = "Le nom du donateur est requis")
    private String nomDonateur;
    
    @NotNull(message = "Le montant est requis")
    @Positive(message = "Le montant doit être positif")
    private Double montant;
    
    private String nomCampagne;
    private LocalDateTime date;
    public void setMontant(BigDecimal montant2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMontant'");
    }
}
