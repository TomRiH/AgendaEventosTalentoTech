package com.TalentoTech.AgendadorEventos.Controllers;


import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping
    public ResponseEntity<Departamento> crearDepartamento(@RequestBody Departamento departamento){
        Departamento departamentoGuardar = departamentoService.guardarDepartamento(departamento);
        return  new ResponseEntity<>(departamentoGuardar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Departamento> buscarDepartamento(@PathVariable int id){
        Optional<Departamento> departamento = departamentoService.buscarById(id);
        return departamento.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public  ResponseEntity<List<Departamento>> obtenerTodos(){
        List<Departamento> departamento = departamentoService.consultarTodos();
        return  new ResponseEntity<>(departamento, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> editarDepartamento(@PathVariable Integer id, @RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoService.editarDepartamento(id, departamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarDepartamento(@PathVariable Integer id) {
        departamentoService.borrarDepartamento(id);
        return ResponseEntity.noContent().build();
    }

}
