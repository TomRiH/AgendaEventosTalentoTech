package com.TalentoTech.AgendadorEventos.Services;


import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class EmprendimientoService {
    //Inyeccion del Repositorio - Llama a la interfaz
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    //Insertar
    public Emprendimiento guardarEmprendimiento(Emprendimiento emprendimiento){
        return emprendimientoRepository.save(emprendimiento);
    }

    //Consultar x id
    public Optional<Emprendimiento> buscarById(Integer id) {
        return emprendimientoRepository.findById(id);
    }

    //Consultar Todos
    public List<Emprendimiento> consultarTodos(){
        return emprendimientoRepository.findAll();
    }

    //Editar
    public Emprendimiento editarEmprendimiento(Integer id, Emprendimiento editarEmprendimiento){
        return emprendimientoRepository.findById(id).map(emprendimiento -> {
            emprendimiento.setCodigo(editarEmprendimiento.getNombre());
            emprendimiento.setNombre(editarEmprendimiento.getNombre());
            emprendimiento.setMunicipio(editarEmprendimiento.getMunicipio());
            emprendimiento.setCategoria(editarEmprendimiento.getCategoria());
            return emprendimientoRepository.save(emprendimiento);
        }).orElseThrow(() -> new RuntimeException("Emprendimiento no encontrado"));
    }

    //Borrar por id
    public void borrarEmprendimiento(Integer id) {emprendimientoRepository.deleteById(id);}


}
