package com.example.architectc.controllers;

import com.example.architectc.projections.CampagneResume;
import com.example.architectc.services.ServiceCampagne;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
public class CampagneController {
    private final ServiceCampagne serviceCampagne;

    public CampagneController(ServiceCampagne serviceCampagne) {
        this.serviceCampagne = serviceCampagne;
    }

    @GetMapping("/actives")
    public List<CampagneResume> getCampagnesActives() {
        return serviceCampagne.getCampagnesActives();
    }
}