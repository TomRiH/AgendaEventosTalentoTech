package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//el servicio es una clase que se encarga de la logica de negocio
@Service
public class EmprendimientoService {
    //inyectar el repositorio
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private MunicipioRepository municipioRepository; // Inyectar MunicipioRepository

    // Crear emprendimiento
    public Emprendimiento crearEmprendimiento(Emprendimiento emprendimiento) {
        // Verificar que el municipio no sea null y que tenga un ID válido
        if (emprendimiento.getMunicipio() == null || emprendimiento.getMunicipio().getId() == null) {
            throw new IllegalArgumentException("Municipio no puede ser nulo");
        }

        // Buscar el municipio en la base de datos
        Optional<Municipio> municipio = municipioRepository.findById(emprendimiento.getMunicipio().getId());
        if (municipio.isEmpty()) {
            throw new IllegalArgumentException("Municipio no encontrado");
        }

        // Asignar el municipio encontrado al emprendimiento
        emprendimiento.setMunicipio(municipio.get());
        return emprendimientoRepository.save(emprendimiento);
    }

    //Mostrar emprendimientos
    public List<Emprendimiento> mostrarEmprendimientos(){
        return emprendimientoRepository.findAll();
    }

    //Buscar emprendimiento por id
    public Optional<Emprendimiento> findById(Integer id) {
        return emprendimientoRepository.findById(id);
    }

    //editar emprendimiento
    public Emprendimiento editarEmprendimiento(Integer id, Emprendimiento editarEmprendimiento){
        return emprendimientoRepository.findById(id).map(emprendimiento -> {
            emprendimiento.setNombre(emprendimiento.getNombre());
            emprendimiento.setMunicipio(emprendimiento.getMunicipio());
            emprendimiento.setFecha_modificacion(emprendimiento.getFecha_modificacion());
            return emprendimientoRepository.save(emprendimiento);
        }).orElseThrow(() -> new RuntimeException("Emprendimiento no encontrado"));
    }

    //eliminar emprendimiento
    public void eliminarEmprendimiento(Integer id){
        emprendimientoRepository.deleteById(id);
    }
}
