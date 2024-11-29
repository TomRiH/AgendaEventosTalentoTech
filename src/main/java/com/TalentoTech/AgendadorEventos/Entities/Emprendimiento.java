package com.TalentoTech.AgendadorEventos.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emprendimiento")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String codigo;

    @Column
    private String nombre;

    @ManyToOne(targetEntity = Municipio.class)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Municipio municipio;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Categoria categoria;

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
