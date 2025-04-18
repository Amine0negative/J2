package com.example.architectc.services;

import com.example.architectc.projections.CampagneResume;
import com.example.architectc.repositories.CampagneRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceCampagne {
    private final CampagneRepository campagneRepository;

    public ServiceCampagne(CampagneRepository campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    public List<CampagneResume> getCampagnesActives() {
        return campagneRepository.findActiveCampagnes(LocalDate.now());
    }
}