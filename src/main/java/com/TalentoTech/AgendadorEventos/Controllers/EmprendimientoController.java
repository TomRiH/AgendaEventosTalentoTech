package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Services.CategoriaService;
import com.TalentoTech.AgendadorEventos.Services.EmprendimientoService;
import com.TalentoTech.AgendadorEventos.Services.MunicipioService;
import com.TalentoTech.AgendadorEventos.dto.EmprendimientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {
    @Autowired
    private EmprendimientoService emprendimientoService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Emprendimiento> guardarEmprendimiento(@RequestBody EmprendimientoDTO emprendimientoDTO) {
        // Recuperar y asignar el objeto Municipio
        Municipio municipio = municipioService.buscarById(emprendimientoDTO.getIdMunicipio())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        // Recuperar y asignar el objeto Categoria
        Categoria categoria = categoriaService.buscarById(emprendimientoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        // Crear el objeto Emprendimiento y asignar Municipio y Categoria
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setCodigo(emprendimientoDTO.getCodigo());
        emprendimiento.setNombre(emprendimientoDTO.getNombre());
        emprendimiento.setMunicipio(municipio);
        emprendimiento.setCategoria(categoria);

        // Mensajes de depuración
        System.out.println("Emprendimiento: " + emprendimiento);
        System.out.println("Municipio: " + municipio);
        System.out.println("Categoria: " + categoria);

        Emprendimiento emprendimientoCrear = emprendimientoService.crearEmprendimiento(emprendimiento);
        return new ResponseEntity<>(emprendimientoCrear, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Emprendimiento>> obtenerTodosLosEmprendimientos() {
        List<Emprendimiento> emprendimientos = emprendimientoService.mostrarEmprendimientos();
        return ResponseEntity.ok(emprendimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprendimiento> buscarEmprendimientoId(@PathVariable Integer id){
        Optional<Emprendimiento> emprendimiento = emprendimientoService.findById(id);
        return emprendimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprendimiento> editarEmprendimiento(@PathVariable Integer id, @RequestBody Emprendimiento editarEmprendimiento){
        Emprendimiento emprendimiento = emprendimientoService.editarEmprendimiento(id, editarEmprendimiento);
        return ResponseEntity.ok(emprendimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Emprendimiento> eliminarEmprendimiento(@PathVariable Integer id){
        emprendimientoService.eliminarEmprendimiento(id);
        return ResponseEntity.noContent().build();
    }
}