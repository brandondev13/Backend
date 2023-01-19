package com.brandon.reto.service;

import com.brandon.reto.model.Usuario;
import com.brandon.reto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements com.brandon.reto.service.interfaces.UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> buscarFechaNacimiento(Long id, LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios == null) {
            throw new IllegalArgumentException("La lista de usuarios es nula");
        }
        if (usuarios.isEmpty()) {
            throw new IllegalArgumentException("La lista de usuarios está vacía");
        }
        if (id != null) {
            usuarios = usuarios.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList());
        }
        usuarios = usuarios.stream().filter(u -> u.getFechaNacimiento().isEqual(fechaNacimiento)).collect(Collectors.toList());
        return usuarios;
    }


    @Override
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (!usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("El usuario con id " + id + " no existe en la base de datos");
        }
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("La contraseña del usuario es obligatoria");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento del usuario es obligatoria");
        }
        if (usuario.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento del usuario es obligatoria");
        }
        if (usuario.getGenero() == null || usuario.getGenero().isEmpty()) {
            throw new IllegalArgumentException("El género del usuario es obligatorio");
        }
        if (usuario.getDireccion() == null || usuario.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección del usuario es obligatoria");
        }
        if (usuario.getTelefono() == null || usuario.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del usuario es obligatorio");
        }
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            throw new IllegalArgumentException("El rol del usuario es obligatorio");
        }

        if (usuario.getMateria() == null || usuario.getMateria().isEmpty()) {
            throw new IllegalArgumentException("La materia del usuario es obligatoria");
        }
        Usuario usuarioActualizado = usuarioExistente.get();
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setContrasena(usuario.getContrasena());
        usuarioActualizado.setCorreo(usuario.getCorreo());
        usuarioActualizado.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioActualizado.setGenero(usuario.getGenero());
        usuarioActualizado.setDireccion(usuario.getDireccion());
        usuarioActualizado.setTelefono(usuario.getTelefono());
        usuarioActualizado.setRol(usuario.getRol());
        usuarioActualizado.setMateria(usuario.getMateria());
        return usuarioRepository.save(usuarioActualizado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

}
