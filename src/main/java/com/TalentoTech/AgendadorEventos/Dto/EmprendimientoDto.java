package com.TalentoTech.AgendadorEventos.Dto;

import lombok.Data;
import java.util.List;

@Data
public class EmprendimientoDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private Integer id_municipio;
    private String nombre_municipio;
    private Integer id_categoria;
    private String nombre_categoria;

    private List<EventoEmprendimientoDto> eventosRelacionados;

    private List<UsuarioRelacionadoDto> usuariosRelacionados;

    public EmprendimientoDto(Integer id, String codigo, String nombre, Integer id_municipio, String nombre_municipio, Integer id_categoria, String nombre_categoria) {
        this.codigo = codigo;
        this.id = id;
        this.nombre = nombre;
        this.id_municipio = id_municipio;
        this.nombre_municipio = nombre_municipio;
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
    }

    public EmprendimientoDto() {

    }
}
