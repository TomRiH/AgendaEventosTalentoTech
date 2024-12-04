package com.TalentoTech.AgendadorEventos.Dto;

public interface EmprendimientoEventoUsuarioDto {
    // Emprendimiento
    Integer getId();
    String getCodigo();
    String getNombre();
    Integer getId_municipio();
    String getNombre_municipio();
    Integer getId_categoria();
    String getNombre_categoria();

    // Evento
    Integer getEventoId();
    String getEventoNombre();

    // Usuario
    Integer getUsuarioId();
    String getUsuarioNombre();
}
