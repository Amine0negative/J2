package com.example.architectc.services;

import com.example.architectc.projections.CampagneResume;
import com.example.architectc.repositories.CampagneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServiceCampagneTest {

    @Mock
    private CampagneRepository campagneRepository;

    private ServiceCampagne serviceCampagne;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceCampagne = new ServiceCampagne(campagneRepository);
    }

    @Test
    void testGetCampagnesActives() {
        // Given
        CampagneResume mockCampagne = createMockCampagneResume();
        when(campagneRepository.findActiveCampagnes(any(LocalDate.class)))
                .thenReturn(Collections.singletonList(mockCampagne));

        // When
        List<CampagneResume> result = serviceCampagne.getCampagnesActives();

        // Then
        assertThat(result)
                .isNotEmpty()
                .hasSize(1);

        CampagneResume firstResult = result.get(0);
        assertThat(firstResult.getId()).isEqualTo(1L);
        assertThat(firstResult.getNom()).isEqualTo("Test Campagne");
        assertThat(firstResult.getObjectifMontant()).isEqualTo(new BigDecimal("1000.00"));
    }

    private CampagneResume createMockCampagneResume() {
        return new CampagneResume() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getNom() {
                return "Test Campagne";
            }

            @Override
            public BigDecimal getObjectifMontant() {
                return new BigDecimal("1000.00");
            }
        };
    }
}