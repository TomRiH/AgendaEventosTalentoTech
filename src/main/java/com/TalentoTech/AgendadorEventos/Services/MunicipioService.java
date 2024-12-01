package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.MunicipioDto;
import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.DepartamentoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Municipio guardarMunicipio(MunicipioDto municipioDto) {
        // Buscar el departamento por ID
        Departamento departamento = departamentoRepository.findById(municipioDto.getId_departamento())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado"));

        // Crear el municipio
        Municipio municipio = new Municipio();
        municipio.setCodigo(municipioDto.getCodigo());
        municipio.setNombre(municipioDto.getNombre());
        municipio.setDepartamento(departamento);

        // Guardar el municipio
        return municipioRepository.save(municipio);
    }

    //consultar Todos
    public List<Municipio> consultarTodos(){
        return  municipioRepository.findAll();
    }

    public Optional<Municipio> buscarById(Integer id) {
        return municipioRepository.findById(id);
    }

    public List<Municipio> findByDepartamentoId(int departamentoId) {
       return municipioRepository.findByDepartamentoId(departamentoId);
    }

    public Municipio editarMunicipio(Integer id, MunicipioDto municipioDto) {

        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        // Buscar el departamento asociado
        Departamento departamento = departamentoRepository.findById(municipioDto.getId_departamento())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado"));

        // Actualizar los valores
        municipio.setCodigo(municipioDto.getCodigo());
        municipio.setNombre(municipioDto.getNombre());
        municipio.setDepartamento(departamento);
        municipio.setFecha_modificacion(java.time.LocalDateTime.now());

        // Guardar los cambios
        return municipioRepository.save(municipio);
    }

    public void borrarMunicipio(Integer id) {
        municipioRepository.deleteById(id);
    }

}
