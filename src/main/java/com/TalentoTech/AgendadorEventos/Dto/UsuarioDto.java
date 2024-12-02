package com.TalentoTech.AgendadorEventos.Dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;
    private String estado;
    private Integer id_municipio;
    private Integer id_rol;
}
