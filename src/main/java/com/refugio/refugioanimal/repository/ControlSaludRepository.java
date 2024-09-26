package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.ControlSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlSaludRepository extends JpaRepository<ControlSalud, Long> {
    ControlSalud findByNombre(String nombre);
}
