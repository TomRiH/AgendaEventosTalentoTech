package com.TalentoTech.AgendadorEventos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data // anotacion de lombok para generar los getter y setter
@Entity //anotacion que indica que es una entidad
@Table(name = "emprendimiento") //anotacion que indica el nombre de la tabla
@NoArgsConstructor //anotacion de lombok para generar el constructor vacio
@AllArgsConstructor //anotacion de lombok para generar el constructor con todos los atributos
@ToString(exclude = {"municipio", "categoria"}) //El metodo toString es para imprimir el objeto
public class Emprendimiento {

    @Id
    @GeneratedValue //anotacion que indica que es autoincremental
    private Integer id;

    @Column(nullable = false, length = 20) //anotacion que indica que no puede ser nulo y el tamaño
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @ManyToOne(targetEntity = Municipio.class)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
    private Municipio municipio;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fecha_modificacion;

    @PrePersist //anotacion que indica que se ejecuta antes de guardar
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();

    }

    @PreUpdate //anotacion que indica que se ejecuta antes de actualizar
    protected void onUpdate() {
        this.fecha_modificacion = LocalDateTime.now();
    }
}
