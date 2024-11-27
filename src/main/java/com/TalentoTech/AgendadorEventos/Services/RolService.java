package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Rol;
import com.TalentoTech.AgendadorEventos.Repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    //insertar
    public Rol guardarRol(Rol rol){
        return  rolRepository.save(rol);
    }

    //Consultar x id
    public Optional<Rol> buscarById(Integer id) {
        return rolRepository.findById(id);
    }

    //consultar Todos
    public List<Rol> consultarTodos(){
        return  rolRepository.findAll();
    }

    public Rol editarRol(Integer id, Rol rolEditar) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolEditar.getNombre());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("rol no encontrado"));
    }

    // Borrar por id
    public void borrarRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
