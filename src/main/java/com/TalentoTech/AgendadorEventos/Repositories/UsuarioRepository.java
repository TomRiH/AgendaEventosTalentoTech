package com.TalentoTech.AgendadorEventos.Repositories;

import com.TalentoTech.AgendadorEventos.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
