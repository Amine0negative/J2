package com.example.architectc.repositories;

import com.example.architectc.entities.Campagne;
import com.example.architectc.projections.CampagneResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface CampagneRepository extends JpaRepository<Campagne, Long> {
    @Query("SELECT c FROM Campagne c WHERE c.dateDebut <= ?1 AND c.dateFin >= ?1")
    List<CampagneResume> findActiveCampagnes(LocalDate currentDate);
}