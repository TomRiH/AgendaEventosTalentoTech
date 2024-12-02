package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.EventoDto;
import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.EventoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    //insertar
    public Evento guardarEvento(EventoDto eventoDto){

        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(eventoDto.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        Evento evento = new Evento();
        evento.setNombre(eventoDto.getNombre());
        evento.setFecha(eventoDto.getFecha());
        evento.setMunicipio(municipio);
        evento.setDireccion(eventoDto.getDireccion());
        evento.setDescripcion(eventoDto.getDescripcion());
        return eventoRepository.save(evento);
    }

    //Consultar x id
    public Optional<Evento> buscarById(Integer id){
        return eventoRepository.findById(id);
    }

    //consultar Todos
    public List<Evento> consultarTodos(){
        return  eventoRepository.findAll();
    }

    //Editar
    public Evento editarEvento(Integer id, EventoDto editarEvento) {
        // Buscar el municipio existente
        Municipio municipio = municipioRepository.findById(editarEvento.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado"));

        return eventoRepository.findById(id).map(evento -> {
            evento.setNombre(editarEvento.getNombre());
            evento.setFecha(editarEvento.getFecha());
            evento.setMunicipio(municipio);
            evento.setDireccion(editarEvento.getDireccion());
            evento.setDescripcion(editarEvento.getDescripcion());
            return eventoRepository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    //Borrar por id
    public void borrarEvento(Integer id) {eventoRepository.deleteById(id);}

}
