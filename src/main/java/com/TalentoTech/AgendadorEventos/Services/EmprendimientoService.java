package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.EmprendimientoDto;
import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.CategoriaRepository;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class EmprendimientoService {
    //Inyeccion del Repositorio - Llama a la interfaz
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Insertar
    public Emprendimiento guardarEmprendimiento(EmprendimientoDto emprendimientoDto){
        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(emprendimientoDto.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        // Buscar la categoria existente
        Categoria categoria = categoriaRepository.findById(emprendimientoDto.getId_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("categoria no encontrada"));

        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setCodigo(emprendimientoDto.getCodigo());
        emprendimiento.setNombre(emprendimientoDto.getNombre());
        emprendimiento.setMunicipio(municipio);
        emprendimiento.setCategoria(categoria);
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
    public Emprendimiento editarEmprendimiento(Integer id, EmprendimientoDto editarEmprendimiento){

        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(editarEmprendimiento.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        // Buscar la categoria existente
        Categoria categoria = categoriaRepository.findById(editarEmprendimiento.getId_categoria())
                .orElseThrow(() -> new ResourceNotFoundException("categoria no encontrada"));

        return emprendimientoRepository.findById(id).map(emprendimiento -> {
            emprendimiento.setCodigo(editarEmprendimiento.getCodigo());
            emprendimiento.setNombre(editarEmprendimiento.getNombre());
            emprendimiento.setMunicipio(municipio);
            emprendimiento.setCategoria(categoria);
            return emprendimientoRepository.save(emprendimiento);
        }).orElseThrow(() -> new RuntimeException("Emprendimiento no encontrado"));
    }

    //Borrar por id
    public void borrarEmprendimiento(Integer id) {emprendimientoRepository.deleteById(id);}

}
