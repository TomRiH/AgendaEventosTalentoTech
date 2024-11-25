package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class DepartamentoService {
    //inyeccion del Repositorio - llama a la interfaz
    @Autowired
    private DepartamentoRepository departamentoRepository;

    //insertar
    public Departamento guardarDepartamento(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    //Consultar x id
    public Optional<Departamento> buscarById(Integer id) {
        return departamentoRepository.findById(id);
    }

    //consultar Todos
    public List<Departamento> consultarTodos(){
        return  departamentoRepository.findAll();
    }

    public Departamento editarDepartamento(Integer id, Departamento editarDepartamento) {
        return departamentoRepository.findById(id).map(departamento -> {
            departamento.setNombre(editarDepartamento.getNombre());
            departamento.setCodigo(editarDepartamento.getCodigo());
            return departamentoRepository.save(departamento);
        }).orElseThrow(() -> new RuntimeException("Departamento no encrontado"));
    }

    // Borrar por id
    public void borrarDepartamento(Integer id) {
        departamentoRepository.deleteById(id);
    }

}
