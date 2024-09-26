package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.RegistroSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSaludRepository extends JpaRepository<RegistroSalud, Long> {
}
