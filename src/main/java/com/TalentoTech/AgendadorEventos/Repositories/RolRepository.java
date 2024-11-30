package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    // Consulta para obtener permisos por rol
    @Query("SELECT r.permisos FROM Rol r WHERE r.id = :rolId")
    List<Object> findPermisosByRolId(@Param("rolId") Integer rolId);
}
