package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio guardarMunicipio(Municipio municipio) {
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

    public Municipio editarMunicipio(Integer id, Municipio editarMunicipio) {
        return municipioRepository.findById(id).map(municipio -> {
            municipio.setNombre(editarMunicipio.getNombre());
            municipio.setCodigo(editarMunicipio.getCodigo());
            municipio.setDepartamento(editarMunicipio.getDepartamento());
            return municipioRepository.save(municipio);
        }).orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
    }

    public void borrarMunicipio(Integer id) {
        municipioRepository.deleteById(id);
    }

}
