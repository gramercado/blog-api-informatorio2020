package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value="SELECT * FROM Usuario WHERE ciudad LIKE lower(?1)",nativeQuery=true)
    List<Usuario> getUsuariosPorCiudad(String ciudad);

    @Query(value="SELECT * FROM Usuario WHERE FECHA_DE_ALTA >= ?1", nativeQuery = true)
    List<Usuario> getUsuariosPorFecha(String fecha);
}
