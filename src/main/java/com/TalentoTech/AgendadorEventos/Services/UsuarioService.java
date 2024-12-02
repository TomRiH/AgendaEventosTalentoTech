package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.UsuarioDto;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Entities.Rol;
import com.TalentoTech.AgendadorEventos.Entities.Usuario;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import com.TalentoTech.AgendadorEventos.Repositories.RolRepository;
import com.TalentoTech.AgendadorEventos.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private RolRepository rolRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(UsuarioDto usuarioDto) {

        // Buscar municipio
        Municipio municipio = municipioRepository.findById(usuarioDto.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio con el id " + usuarioDto.getId_municipio() + " no encontrado"));

        // Buscar rol
        Rol rol = rolRepository.findById(usuarioDto.getId_rol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol con id " + usuarioDto.getId_rol() + " no encontrado"));

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setCedula(usuarioDto.getCedula());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setMunicipio(municipio);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario
    public Usuario editarUsuario(Integer id, UsuarioDto usuarioDTO) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con el id numero " + id + " no encontrado"));

        // Buscar municipio
        Municipio municipio = municipioRepository.findById(usuarioDTO.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio con el id numero " + usuarioDTO.getId_municipio() + " no encontrado"));

        // Buscar rol
        Rol rol = rolRepository.findById(usuarioDTO.getId_rol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol con el id numero " + usuarioDTO.getId_rol() + " no encontrado"));

        // Actualizar los campos
        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEstado(Boolean.valueOf(usuarioDTO.getEstado()));
        usuario.setMunicipio(municipio);
        usuario.setRol(rol);

        // Guardar cambios
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

}
