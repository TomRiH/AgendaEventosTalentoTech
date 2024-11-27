package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
