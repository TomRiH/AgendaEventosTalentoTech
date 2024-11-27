package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Entities.Departamento;
import com.TalentoTech.AgendadorEventos.Entities.Municipio;
import com.TalentoTech.AgendadorEventos.Services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("municipios")
public class MunicipioController {
    @Autowired
    private MunicipioService municipioService;

    @PostMapping
    public ResponseEntity<Municipio> guardarMunicipio(@RequestBody Municipio municipio) {
        return ResponseEntity.ok(municipioService.guardarMunicipio(municipio));
    }

    @GetMapping("/departamento/{codigo}")
    public  ResponseEntity<List<Municipio>> buscarByCode(@PathVariable Integer codigo){
        List<Municipio> municipios = municipioService.findByDepartamentoId(codigo);
        return  new ResponseEntity<>(municipios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> buscarMunicipio(@PathVariable Integer id) {
        return municipioService.buscarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> editarMunicipio(@PathVariable Integer id, @RequestBody Municipio municipio) {
        return ResponseEntity.ok(municipioService.editarMunicipio(id, municipio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMunicipio(@PathVariable Integer id) {
        municipioService.borrarMunicipio(id);
        return ResponseEntity.noContent().build();
    }


}
