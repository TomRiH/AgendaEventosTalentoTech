package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// el repositorio es una interfaz que extiende de la interfaz JpaRepository
@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Integer> {


}
