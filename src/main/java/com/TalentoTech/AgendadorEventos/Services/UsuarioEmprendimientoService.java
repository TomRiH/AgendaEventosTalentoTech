package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.UsuarioEmprendimiento;
import com.TalentoTech.AgendadorEventos.Repositories.UsuarioEmprendimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioEmprendimientoService {
    @Autowired
    private UsuarioEmprendimientoRepository usuarioEmprendimientoRepository;
    // Método para guardar un usuarioEmprendimiento
    public List<UsuarioEmprendimiento> findAll() {
        return usuarioEmprendimientoRepository.findAll();
    }
    // Metodo para buscar un usuarioEmprendimiento por id
    public Optional<UsuarioEmprendimiento> findById(Integer id) {
        return usuarioEmprendimientoRepository.findById(id);
    }

    // Metodo para guardar un usuarioEmprendimiento
    public UsuarioEmprendimiento save(UsuarioEmprendimiento usuarioEmprendimiento) {
        return usuarioEmprendimientoRepository.save(usuarioEmprendimiento);
    }

    // Metodo para eliminar un usuarioEmprendimiento
    public void deleteById(Integer id) {
        usuarioEmprendimientoRepository.deleteById(id);
    }

}
