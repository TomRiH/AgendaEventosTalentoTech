package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Dto.EmprendimientoDetalleDto;
import com.TalentoTech.AgendadorEventos.Dto.EmprendimientoEventoUsuarioDto;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Integer> {
    @Query(value = """
        SELECT DISTINCT e.id AS id, 
                        e.codigo AS codigo, 
                        e.nombre AS nombre,
                        e.id_municipio AS id_municipio, 
                        mu.nombre AS nombre_municipio,
                        e.id_categoria AS id_categoria,
                        ca.nombre AS nombre_categoria,
                        ev.id AS eventoId, 
                        ev.nombre AS eventoNombre,
                        us.id AS usuarioId, 
                        us.nombre AS usuarioNombre
        FROM emprendimiento e
        LEFT JOIN municipio mu ON e.id_municipio = mu.id
        LEFT JOIN categoria ca ON e.id_categoria = ca.id
        LEFT JOIN evento_emprendimiento ee ON e.id = ee.id_emprendimiento
        LEFT JOIN evento ev ON ee.id_evento = ev.id
        LEFT JOIN usuario_emprendimiento ue ON e.id = ue.id_emprendimiento
        LEFT JOIN usuario us ON ue.id_usuario = us.id
        WHERE e.id = :id
        """, nativeQuery = true)
    List<EmprendimientoDetalleDto> findByIdWithEventos(@Param("id") Integer id);

    @Query(value = """
        SELECT DISTINCT e.id AS id, 
                        e.codigo AS codigo, 
                        e.nombre AS nombre,
                        e.id_municipio AS id_municipio, 
                        mu.nombre AS nombre_municipio,
                        e.id_categoria AS id_categoria,
                        ca.nombre AS nombre_categoria,
                        ev.id AS eventoId, 
                        ev.nombre AS eventoNombre,
                        us.id AS usuarioId, 
                        us.nombre AS usuarioNombre
        FROM emprendimiento e
        LEFT JOIN municipio mu ON e.id_municipio = mu.id
        LEFT JOIN categoria ca ON e.id_categoria = ca.id
        LEFT JOIN evento_emprendimiento ee ON e.id = ee.id_emprendimiento
        LEFT JOIN evento ev ON ee.id_evento = ev.id
        LEFT JOIN usuario_emprendimiento ue ON e.id = ue.id_emprendimiento
        LEFT JOIN usuario us ON ue.id_usuario = us.id
        ORDER BY e.id DESC
        """, nativeQuery = true)
    List<EmprendimientoEventoUsuarioDto> findAllWithEventosAndUsuarios();

}
