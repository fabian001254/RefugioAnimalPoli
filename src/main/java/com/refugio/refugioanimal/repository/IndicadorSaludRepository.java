package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.IndicadorSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorSaludRepository extends JpaRepository<IndicadorSalud, Long> {
}
