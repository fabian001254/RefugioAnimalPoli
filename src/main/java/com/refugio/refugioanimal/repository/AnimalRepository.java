package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByNombre(String nombre);
}
