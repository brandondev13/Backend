package com.brandon.reto.model;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nombre;


    @NotNull
    @Column(nullable = false)
    private String estado;

    @ManyToOne
    private Usuario usuarioAsignado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate fechaFinalizacion;

    public Tarea(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setUsuarioAsignado(String usuarioAsignado) {}

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
}
