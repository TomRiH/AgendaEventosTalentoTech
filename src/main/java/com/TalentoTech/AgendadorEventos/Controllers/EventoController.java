package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/evento")

public class EventoController {

    @Autowired
    private EventoService eventoService;

    //insertar
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento){
        Evento eventoGuardar = eventoService.guardarEvento(evento);
        return new ResponseEntity<>(eventoGuardar, HttpStatus.CREATED);
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoId(@PathVariable int id){
        Optional<Evento> evento = eventoService.buscarById(id);
        return evento.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }


}
