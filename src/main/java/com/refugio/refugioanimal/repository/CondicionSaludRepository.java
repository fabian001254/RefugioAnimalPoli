package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.CondicionSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicionSaludRepository extends JpaRepository<CondicionSalud, Long> {
}
