package com.TalentoTech.AgendadorEventos.Dto;

public interface EmprendimientoDetalleDto {

    // Emprendimiento
    Integer getId();
    String getCodigo();
    String getNombre();

    // Municipio
    Integer getIdMunicipio();
    String getNombreMunicipio();

    // Categoría
    Integer getIdCategoria();
    String getNombreCategoria();

    // Evento
    Integer getEventoId();
    String getEventoNombre();

    // Usuario
    Integer getUsuarioId();
    String getUsuarioNombre();
}
