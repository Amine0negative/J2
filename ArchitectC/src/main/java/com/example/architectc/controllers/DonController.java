package com.example.architectc.controllers;

import com.example.architectc.dtos.DonDTO;
import com.example.architectc.services.ServiceDon;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campagnes")
public class DonController {
    private final ServiceDon serviceDon;

    public DonController(ServiceDon serviceDon) {
        this.serviceDon = serviceDon;
    }

    @PostMapping("/{id}/dons")
    public ResponseEntity<DonDTO> enregistrerDon(
            @PathVariable Long id,
            @Valid @RequestBody DonDTO donDTO) {
        return ResponseEntity.ok(serviceDon.enregistrerDon(id, donDTO));
    }
}