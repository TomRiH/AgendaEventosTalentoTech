package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Categoria;
import com.TalentoTech.AgendadorEventos.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaGuardar = categoriaService.guardarCategoria(categoria);
        return  new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaId(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaService.buscarById(id);
        return  categoria.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public  ResponseEntity<List<Categoria>> obtenerTodos(){
        List<Categoria> categoria = categoriaService.consultarTodos();
        return  new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategory(
            @PathVariable Integer id,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.editarCategory(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> borrarCategoria(@PathVariable Integer id) {
        categoriaService.borrarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
