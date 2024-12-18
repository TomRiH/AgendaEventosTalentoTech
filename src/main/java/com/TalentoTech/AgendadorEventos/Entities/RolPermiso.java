package com.TalentoTech.AgendadorEventos.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "rol_permiso")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Permiso permiso;
  
}
