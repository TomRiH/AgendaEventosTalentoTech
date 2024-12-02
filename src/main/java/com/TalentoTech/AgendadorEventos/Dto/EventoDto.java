package com.TalentoTech.AgendadorEventos.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoDto {
    private String nombre;
    private LocalDateTime fecha;
    private Integer id_municipio;
    private String direccion;
    private String descripcion;

}
