package com.example.architectc.controllers;

import com.example.architectc.dtos.DonDTO;
import com.example.architectc.services.ServiceDon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DonController.class)
class DonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceDon serviceDon;

    @Autowired
    private ObjectMapper objectMapper;

    private DonDTO donDTO;
    private static final Long CAMPAGNE_ID = 1L;

    @BeforeEach
    void setUp() {
        donDTO = new DonDTO();
        donDTO.setId(1L);
        donDTO.setNomDonateur("John Doe");
        donDTO.setMontant(new BigDecimal("100.00"));
        donDTO.setNomCampagne("Campagne Test");
        donDTO.setDate(LocalDateTime.now());
    }

    @Test
    void whenCreateDon_thenReturnCreatedDon() throws Exception {
        when(serviceDon.enregistrerDon(eq(CAMPAGNE_ID), any(DonDTO.class)))
                .thenReturn(donDTO);

        mockMvc.perform(post("/api/campagnes/{campagneId}/dons", CAMPAGNE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomDonateur").value(donDTO.getNomDonateur()))
                .andExpect(jsonPath("$.montant").value(donDTO.getMontant()));
    }

    @Test
    void whenCreateDonWithInvalidAmount_thenReturnBadRequest() throws Exception {
        donDTO.setMontant(new BigDecimal("-100.00"));

        mockMvc.perform(post("/api/campagnes/{campagneId}/dons", CAMPAGNE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateDonForNonExistentCampagne_thenReturnNotFound() throws Exception {
        when(serviceDon.enregistrerDon(eq(CAMPAGNE_ID), any(DonDTO.class)))
                .thenThrow(new EntityNotFoundException("Campagne non trouvée"));

        mockMvc.perform(post("/api/campagnes/{campagneId}/dons", CAMPAGNE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetDonById_thenReturnDon() throws Exception {
        when(serviceDon.getDonById(1L)).thenReturn(donDTO);

        mockMvc.perform(get("/api/dons/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(donDTO.getId()))
                .andExpect(jsonPath("$.nomDonateur").value(donDTO.getNomDonateur()));
    }

    @Test
    void whenGetDonsByCompagne_thenReturnDonsList() throws Exception {
        when(serviceDon.getAllDonsByCampagne(CAMPAGNE_ID))
                .thenReturn(Arrays.asList(donDTO));

        mockMvc.perform(get("/api/campagnes/{campagneId}/dons", CAMPAGNE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomDonateur").value(donDTO.getNomDonateur()));
    }
    
    @Test
    void whenGetNonExistentDon_thenReturnNotFound() throws Exception {
        when(serviceDon.getDonById(999L))
                .thenThrow(new EntityNotFoundException("Don non trouvé"));

        mockMvc.perform(get("/api/dons/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
