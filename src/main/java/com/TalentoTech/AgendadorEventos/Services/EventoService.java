package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    //Inyeccion del repositorio - llama interfaz
    @Autowired
    private EventoRepository eventoRepository;

    //insertar
    public Evento guardarEvento(Evento evento){
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
    public Evento editarEvento(Integer id, Evento editarEvento) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNombre(editarEvento.getNombre());
            evento.setFecha(editarEvento.getFecha());
            evento.setId_municipio(editarEvento.getId_municipio());
            evento.setDireccion(editarEvento.getDireccion());
            evento.setDescripcion(editarEvento.getDescripcion());
            return eventoRepository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    //Borrar por id
    public void borrarEvento(Integer id) {eventoRepository.deleteById(id);}

}
