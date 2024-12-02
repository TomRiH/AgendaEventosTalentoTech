package com.TalentoTech.AgendadorEventos.Services;

import com.TalentoTech.AgendadorEventos.Dto.AsignarEmprendimientoDTO;
import com.TalentoTech.AgendadorEventos.Entities.Emprendimiento;
import com.TalentoTech.AgendadorEventos.Entities.Evento;
import com.TalentoTech.AgendadorEventos.Entities.EventoEmprendimiento;
import com.TalentoTech.AgendadorEventos.Exception.ResourceNotFoundException;
import com.TalentoTech.AgendadorEventos.Repositories.EmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.EventoEmprendimientoRepository;
import com.TalentoTech.AgendadorEventos.Repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoEmprendimientoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private EventoEmprendimientoRepository eventoEmprendimientoRepository;

    public EventoEmprendimiento asignarEmprendimiento(AsignarEmprendimientoDTO dto) {
        // Verificar que el evento existe
        Evento evento = eventoRepository.findById(dto.getId_evento())
                .orElseThrow(() -> new ResourceNotFoundException("Evento con el id numero " + dto.getId_evento() + " no existe"));

        // Verificar que el emprendimiento existe
        Emprendimiento emprendimiento = emprendimientoRepository.findById(dto.getId_emprendimiento())
                .orElseThrow(() -> new ResourceNotFoundException("Emprendimiento con el id numero" + dto.getId_emprendimiento() + " no existe"));

        EventoEmprendimiento eventoEmprendimiento = new EventoEmprendimiento();
        eventoEmprendimiento.setEvento(evento);
        eventoEmprendimiento.setEmprendimiento(emprendimiento);
        return eventoEmprendimientoRepository.save(eventoEmprendimiento);
    }
}
