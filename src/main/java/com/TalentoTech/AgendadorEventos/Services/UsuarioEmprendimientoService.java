package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.AsignarUsuarioEmprendimientoDTO;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Usuario;
import com.TalentoTech.AgendadorEventos.Entities.UsuarioEmprendimiento;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.UsuarioEmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioEmprendimientoService {

    @Autowired
    private UsuarioEmprendimientoRepository usuarioEmprendimientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

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

    // Asignar un usuario a un emprendimiento
    public UsuarioEmprendimiento asignarUsuarioEmprendimiento(AsignarUsuarioEmprendimientoDTO dto) {
        // Verificar que el usuario existe
        Usuario usuario = usuarioRepository.findById(dto.getId_usuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + dto.getId_usuario() + " no encontrado"));

        // Verificar que el emprendimiento existe
        Emprendimiento emprendimiento = emprendimientoRepository.findById(dto.getId_emprendimiento())
                .orElseThrow(() -> new ResourceNotFoundException("Emprendimiento con id " + dto.getId_emprendimiento() + " no encontrado"));

        // Crear la relación
        UsuarioEmprendimiento usuarioEmprendimiento = new UsuarioEmprendimiento();
        usuarioEmprendimiento.setUsuario(usuario);
        usuarioEmprendimiento.setEmprendimiento(emprendimiento);

        // Guardar la relación
        return usuarioEmprendimientoRepository.save(usuarioEmprendimiento);
    }

    // Listar todos los emprendimientos asignados a un usuario
    public List<UsuarioEmprendimiento> obtenerEmprendimientosPorUsuario(Integer idUsuario) {
        return usuarioEmprendimientoRepository.findByUsuarioId(idUsuario);
    }

    // Listar todos los usuarios asignados a un emprendimiento
    public List<UsuarioEmprendimiento> obtenerUsuariosPorEmprendimiento(Integer idEmprendimiento) {
        return usuarioEmprendimientoRepository.findByEmprendimientoId(idEmprendimiento);
    }

}
