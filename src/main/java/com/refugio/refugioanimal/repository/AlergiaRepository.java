package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {
}
