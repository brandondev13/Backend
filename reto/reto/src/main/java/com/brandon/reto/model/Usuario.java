package com.brandon.reto.model;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String materia;

    public Usuario(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario(long id, String nombre, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario(Long id, String nombre) {

    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
