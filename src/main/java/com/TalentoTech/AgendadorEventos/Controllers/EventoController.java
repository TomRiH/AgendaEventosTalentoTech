package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    //Encontrar por Id
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoId(@PathVariable int id){
        Optional<Evento> evento = eventoService.buscarById(id);
        return evento.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Consultar Todos
    @GetMapping
    public  ResponseEntity<List<Evento>> obtenerTodos(){
        List<Evento> evento = eventoService.consultarTodos();
        return  new ResponseEntity<>(evento, HttpStatus.OK);
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Evento> editarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.editarEvento(id, evento));
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEvento(@PathVariable Integer id) {
        eventoService.borrarEvento(id);
        return  ResponseEntity.noContent().build();
    }


}
