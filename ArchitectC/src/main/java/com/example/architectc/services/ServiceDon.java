package com.example.architectc.services;

import com.example.architectc.dtos.DonDTO;
import com.example.architectc.entities.Don;
import com.example.architectc.entities.Campagne;
import com.example.architectc.repositories.DonRepository;
import com.example.architectc.repositories.CampagneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
@Transactional
public class ServiceDon {
    private final DonRepository donRepository;
    private final CampagneRepository campagneRepository;

    public ServiceDon(DonRepository donRepository, CampagneRepository campagneRepository) {
        this.donRepository = donRepository;
        this.campagneRepository = campagneRepository;
    }

    public DonDTO enregistrerDon(Long campagneId, DonDTO donDTO) {
        Campagne campagne = campagneRepository.findById(campagneId)
                .orElseThrow(() -> new EntityNotFoundException("Campagne non trouvée avec l'ID: " + campagneId));

        Don don = new Don();
        don.setNomDonateur(donDTO.getNomDonateur());
        don.setMontant(BigDecimal.valueOf(donDTO.getMontant().doubleValue()));
        don.setDate(donDTO.getDate());
        don.setCampagne(campagne);

        Don savedDon = donRepository.save(don);
        return convertToDTO(savedDon);
    }

    public DonDTO getDonById(long id) {
        Don don = donRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Don non trouvé avec l'ID: " + id));
        return convertToDTO(don);
    }

    private DonDTO convertToDTO(Don don) {
        DonDTO dto = new DonDTO();
        dto.setId(don.getId());
        dto.setNomDonateur(don.getNomDonateur());
        dto.setMontant(BigDecimal.valueOf(don.getMontant().doubleValue()));
        dto.setDate(don.getDate());
        dto.setNomCampagne(don.getCampagne().getNom());
        return dto;
    }
}