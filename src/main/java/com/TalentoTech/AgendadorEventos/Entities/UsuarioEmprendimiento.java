package com.TalentoTech.AgendadorEventos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario_emprendimiento")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioEmprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne(targetEntity = Emprendimiento.class)
    @JoinColumn(name = "id_emprendimiento", referencedColumnName = "id", nullable = false)
    private Emprendimiento emprendimiento;

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
