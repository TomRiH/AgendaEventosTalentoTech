package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Permiso;
import com.TalentoTech.AgendadorEventos.Repositories.PermisoReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService {

    @Autowired
    private PermisoReporitory permisoReporitory;

    //insertar
    public Permiso guardarPermiso(Permiso permiso){
        return  permisoReporitory.save(permiso);
    }

    //Consultar x id
    public Optional<Permiso> buscarById(Integer id) {
        return permisoReporitory.findById(id);
    }

    //consultar Todos
    public List<Permiso> consultarTodos(){
        return  permisoReporitory.findAll();
    }

    public Permiso editarPermiso(Integer id, Permiso permisoEditar) {
        return permisoReporitory.findById(id).map(permiso -> {
            permiso.setNombre(permisoEditar.getNombre());
            permiso.setDescripcion(permisoEditar.getDescripcion());
            return permisoReporitory.save(permiso);
        }).orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    }

    // Borrar por id
    public void borrarPermiso(Integer id) {
        permisoReporitory.deleteById(id);
    }
}
