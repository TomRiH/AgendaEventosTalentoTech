package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.UsuarioEmprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioEmprendimientoRepository extends JpaRepository<UsuarioEmprendimiento, Integer> {
    // Métodos personalizados
    List<UsuarioEmprendimiento> findByUsuarioId(Integer usuarioId);
    List<UsuarioEmprendimiento> findByEmprendimientoId(Integer emprendimientoId);
}