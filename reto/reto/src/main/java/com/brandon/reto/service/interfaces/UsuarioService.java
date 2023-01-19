package com.brandon.reto.service.interfaces;

import com.brandon.reto.model.Usuario;
import java.time.LocalDate;
import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();

    Usuario obtenerPorId(Long id);

    List<Usuario> buscarFechaNacimiento(Long id, LocalDate fechaNacimiento);

    Usuario crear(Usuario usuario);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);
}
