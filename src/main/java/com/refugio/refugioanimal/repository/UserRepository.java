package com.refugio.refugioanimal.repository;

import com.refugio.refugioanimal.model.Usuario;
import com.refugio.refugioanimal.model.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    // Obtener usuarios por rol
    List<Usuario> findByRol(Rol rol);

    // Obtener un usuario por nombre de usuario
    Usuario findByNombreUsuario(String nombreUsuario);
}
