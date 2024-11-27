package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Entities.Permiso;
import com.TalentoTech.AgendadorEventos.Services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permisos")
public class PermisoController {
    @Autowired
    private PermisoService permisoService;

    @PostMapping
    public ResponseEntity<Permiso> crearPermiso(@RequestBody Permiso permiso){
        Permiso permisoGuardar = permisoService.guardarPermiso(permiso);
        return  new ResponseEntity<>(permiso, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> buscarPermisoId(@PathVariable Integer id){
        Optional<Permiso> permiso = permisoService.buscarById(id);
        return  permiso.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public  ResponseEntity<List<Permiso>> obtenerTodos(){
        List<Permiso> permiso = permisoService.consultarTodos();
        return  new ResponseEntity<>(permiso, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permiso> editarPermiso(
            @PathVariable Integer id,
            @RequestBody Permiso permiso) {
        return ResponseEntity.ok(permisoService.editarPermiso(id, permiso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Permiso> borrarPermiso(@PathVariable Integer id) {
        permisoService.borrarPermiso(id);
        return ResponseEntity.noContent().build();
    }

}
