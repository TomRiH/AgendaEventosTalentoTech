package com.TalentoTech.AgendadorEventos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "evento")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false)
    private LocalDateTime fecha;

    //@Column(nullable = false)
    //private Integer id_municipio;

    @ManyToOne(targetEntity = Municipio.class)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
    //@JsonIgnore
    private Municipio municipio;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fecha_modificacion;

    @PrePersist
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fecha_modificacion = LocalDateTime.now();
    }

}
