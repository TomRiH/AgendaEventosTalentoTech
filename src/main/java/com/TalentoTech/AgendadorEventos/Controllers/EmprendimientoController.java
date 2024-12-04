package com.TalentoTech.AgendadorEventos.Controllers;


import com.TalentoTech.AgendadorEventos.Dto.EmprendimientoDto;
import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Services.EmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("emprendimiento")
public class EmprendimientoController {
    @Autowired
    private EmprendimientoService emprendimientoService;

    //Crear
    @PostMapping
    public ResponseEntity<Emprendimiento> crearEmprendimiento(@RequestBody EmprendimientoDto emprendimiento) {
        Emprendimiento emprendimientoGuardar = emprendimientoService.guardarEmprendimiento(emprendimiento);
        return new ResponseEntity<>(emprendimientoGuardar, HttpStatus.CREATED);
    }

    //Buscar por Id
    @GetMapping("/{id}")
    public  ResponseEntity<Emprendimiento> buscarEmprendimiento(@PathVariable int id){
        Optional<Emprendimiento> emprendimiento = emprendimientoService.buscarById(id);
        return emprendimiento.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Buscar todos
    @GetMapping
    public ResponseEntity<List<Emprendimiento>> obtenerTodos(){
        List<Emprendimiento> emprendimiento = emprendimientoService.consultarTodos();
        return new ResponseEntity<>(emprendimiento, HttpStatus.OK);
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Emprendimiento> editarEmprendimiento(@PathVariable Integer id, @RequestBody EmprendimientoDto emprendimiento){
        return ResponseEntity.ok(emprendimientoService.editarEmprendimiento(id,emprendimiento));
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEmprendimiento(@PathVariable Integer id){
        emprendimientoService.borrarEmprendimiento(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los emprendimientos
    @GetMapping("/todos")
    public List<EmprendimientoDto> obtenerTodosLosEmprendimientos() {
        return emprendimientoService.obtenerTodosLosEmprendimientos();
    }

    @GetMapping("/id/{id}")
    public EmprendimientoDto obtenerEmprendimiento(@PathVariable Integer id) {
        return emprendimientoService.obtenerEmprendimientoConRelaciones(id);
    }

}
