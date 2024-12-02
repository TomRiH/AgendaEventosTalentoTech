package com.TalentoTech.AgendadorEventos.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "departamento")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // comentar si se desea traer el listado de los municipios por departamento
    private List<Municipio> municipios;

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
