package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Rol;
import com.TalentoTech.AgendadorEventos.Services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol){
        Rol rolGuardar = rolService.guardarRol(rol);
        return  new ResponseEntity<>(rol, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscarRolId(@PathVariable Integer id){
        Optional<Rol> rol = rolService.buscarById(id);
        return  rol.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/permisos/{id}")
    public List<Object> buscarPermisosByRol(@PathVariable Integer id) {
        return rolService.buscarPermisosByRol(id);
    }

    @GetMapping
    public  ResponseEntity<List<Rol>> obtenerTodos(){
        List<Rol> rol = rolService.consultarTodos();
        return  new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> editarRol(
            @PathVariable Integer id,
            @RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.editarRol(id, rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> borrarRol(@PathVariable Integer id) {
        rolService.borrarRol(id);
        return ResponseEntity.noContent().build();
    }

}
