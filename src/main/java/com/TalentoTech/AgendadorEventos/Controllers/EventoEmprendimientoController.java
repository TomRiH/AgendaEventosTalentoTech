package com.TalentoTech.AgendadorEventos.Controllers;

import com.TalentoTech.AgendadorEventos.Dto.AsignarEmprendimientoDTO;
import com.TalentoTech.AgendadorEventos.Entities.EventoEmprendimiento;
import com.TalentoTech.AgendadorEventos.Services.EventoEmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evento-emprendimiento")
public class EventoEmprendimientoController {

    @Autowired
    private EventoEmprendimientoService eventoEmprendimientoService;

    @PostMapping("/asignar")
    public EventoEmprendimiento asignarEmprendimiento(@RequestBody AsignarEmprendimientoDTO dto) {
        return eventoEmprendimientoService.asignarEmprendimiento(dto);
    }
}
