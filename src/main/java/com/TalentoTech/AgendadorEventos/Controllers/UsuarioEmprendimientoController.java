package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Dto.AsignarUsuarioEmprendimientoDTO;
import com.TalentoTech.AgendadorEventos.Entities.UsuarioEmprendimiento;
import com.TalentoTech.AgendadorEventos.Services.UsuarioEmprendimientoService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarioEmprendimiento")
public class UsuarioEmprendimientoController {

    @Autowired
    private UsuarioEmprendimientoService usuarioEmprendimientoService;

    @GetMapping
    public ResponseEntity<List<UsuarioEmprendimiento>> getAll() {
        return ResponseEntity.ok(usuarioEmprendimientoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEmprendimiento> getById(@PathVariable Integer id) {
        Optional<UsuarioEmprendimiento> usuarioEmprendimiento = usuarioEmprendimientoService.findById(id);
        return usuarioEmprendimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioEmprendimiento> save(@RequestBody UsuarioEmprendimiento usuarioEmprendimiento) {
        return ResponseEntity.ok(usuarioEmprendimientoService.save(usuarioEmprendimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEmprendimiento> update(@PathVariable Integer id, @RequestBody UsuarioEmprendimiento usuarioEmprendimiento){
        if(!usuarioEmprendimientoService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        usuarioEmprendimiento.setId(id);
        UsuarioEmprendimiento updatedUsuarioEmprendimiento = usuarioEmprendimientoService.save(usuarioEmprendimiento);
        return ResponseEntity.ok(updatedUsuarioEmprendimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if(!usuarioEmprendimientoService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        usuarioEmprendimientoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Asignar usuario a un emprendimiento
    @PostMapping("/asignar")
    public UsuarioEmprendimiento asignarUsuarioEmprendimiento(@RequestBody AsignarUsuarioEmprendimientoDTO dto) {
        return usuarioEmprendimientoService.asignarUsuarioEmprendimiento(dto);
    }

    // Obtener emprendimientos por usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<UsuarioEmprendimiento> obtenerEmprendimientosPorUsuario(@PathVariable Integer idUsuario) {
        return usuarioEmprendimientoService.obtenerEmprendimientosPorUsuario(idUsuario);
    }

    // Obtener usuarios por emprendimiento
    @GetMapping("/emprendimiento/{idEmprendimiento}")
    public List<UsuarioEmprendimiento> obtenerUsuariosPorEmprendimiento(@PathVariable Integer idEmprendimiento) {
        return usuarioEmprendimientoService.obtenerUsuariosPorEmprendimiento(idEmprendimiento);
    }

}
