package com.TalentoTech.AgendadorEventos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"municipio", "rol"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String cedula;

    @Column(length = 30, nullable = false)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String apellido;

    @Column(length = 12, nullable = false)
    private String telefono;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne(targetEntity = Municipio.class)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
    private Municipio municipio;

    @ManyToOne(targetEntity = Rol.class)
    @JoinColumn(name = "id_rol", referencedColumnName = "id", nullable = false)
    private Rol rol;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fecha_modificacion;

    @PrePersist
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();
        this.estado = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fecha_modificacion = LocalDateTime.now();
    }

}
