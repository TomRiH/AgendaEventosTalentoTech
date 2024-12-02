package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.EventoEmprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoEmprendimientoRepository extends JpaRepository<EventoEmprendimiento, Integer> {
}
