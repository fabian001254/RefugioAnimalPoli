package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.Administrador;
import com.refugio.refugioanimal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador,Long> {
    Usuario findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);

}
