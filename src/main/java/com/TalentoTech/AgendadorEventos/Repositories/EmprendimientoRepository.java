package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Integer> {
}
