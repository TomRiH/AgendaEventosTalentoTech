package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Dto.UsuarioDto;
import com.TalentoTech.AgendadorEventos.Entities.Usuario;
import com.TalentoTech.AgendadorEventos.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> buscarUsuarios() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto usuario) {
        Usuario usuarioCreado = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.editarUsuario(id, usuario));
    }

}
