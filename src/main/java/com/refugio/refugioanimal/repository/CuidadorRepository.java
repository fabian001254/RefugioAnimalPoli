package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.model.Usuario;
import com.refugio.refugioanimal.model.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Long> {
    List<Usuario> findByRol(Rol rol);

    Usuario findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);

}
