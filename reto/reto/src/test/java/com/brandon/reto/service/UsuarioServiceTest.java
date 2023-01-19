package com.brandon.reto.service;

import com.brandon.reto.model.Usuario;
import com.brandon.reto.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testObtenerTodos() {
        List<Usuario> usuariosEsperados = Arrays.asList(new Usuario(1, "Juan"), new Usuario(2, "Pedro"));
        when(usuarioRepository.findAll()).thenReturn(usuariosEsperados);
        List<Usuario> usuariosObtenidos = usuarioService.obtenerTodos();
        assertEquals(usuariosEsperados, usuariosObtenidos);
    }

    @Test
    public void testObtenerPorId() {
        Usuario usuarioEsperado = new Usuario(1, "Juan");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioEsperado));
        Usuario usuarioObtenido = usuarioService.obtenerPorId(1L);
        assertEquals(usuarioEsperado, usuarioObtenido);
    }

    @Test
    public void testCrear() {
        Usuario usuario = new Usuario(1L, "Juan");
        usuario.setFechaNacimiento(LocalDate.now());
        usuario.setCorreo("juan@gmail.com");
        usuario.setGenero("Masculino");
        usuario.setDireccion("Calle 123");
        usuario.setTelefono("555-5555");
        usuario.setRol("Estudiante");
        usuario.setMateria("Matematicas");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario usuarioCreado = usuarioService.crear(usuario);
        assertEquals(usuario, usuarioCreado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizar() {
        Usuario usuarioExistente = new Usuario(1L, "Juan");
        usuarioExistente.setFechaNacimiento(LocalDate.now());
        usuarioExistente.setCorreo("juan@gmail.com");
        usuarioExistente.setGenero("Masculino");
        usuarioExistente.setDireccion("Calle 123");
        usuarioExistente.setTelefono("555-5555");
        usuarioExistente.setRol("Estudiante");
        usuarioExistente.setMateria("Matematicas");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        Usuario usuarioActualizado = new Usuario(1L, "Juan");
        usuarioActualizado.setFechaNacimiento(LocalDate.now());
        usuarioActualizado.setCorreo("juan123@gmail.com");
        usuarioActualizado.setGenero("Masculino");
        usuarioActualizado.setDireccion("Calle 456");
        usuarioActualizado.setTelefono("555-5555");
        usuarioActualizado.setRol("Estudiante");
        usuarioActualizado.setMateria("Matematicas");
        Usuario usuarioObtenido = usuarioService.actualizar(1L, usuarioActualizado);
        assertEquals(usuarioActualizado, usuarioObtenido);
        verify(usuarioRepository).save(usuarioActualizado);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConUsuarioNulo() {

        usuarioService.actualizar(1L, null);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConUsuarioIdNulo() {

        Usuario usuario = new Usuario();
        usuarioService.actualizar(null, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConUsuarioNoExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConUsuarioNombreNulo() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        Usuario usuario = new Usuario();
        usuario.setNombre(null);
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConUsuarioContrasenaNula() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        Usuario usuario = new Usuario();
        usuario.setContrasena(null);
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test
    public void testActualizarConNombreNulo() {
        Usuario usuario = new Usuario();
        usuario.setNombre(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        try {
            usuarioService.actualizar(1L, usuario);
            fail("Se esperaba una excepción de IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del usuario es obligatorio", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConContrasenaNula() {
        Usuario usuario = new Usuario();
        usuario.setContrasena(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConCorreoNulo() {
        Usuario usuario = new Usuario();
        usuario.setCorreo(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConFechaNacimientoNula() {
        Usuario usuario = new Usuario();
        usuario.setFechaNacimiento(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConGeneroNulo() {
        Usuario usuario = new Usuario();
        usuario.setGenero(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConDireccionNula() {
        Usuario usuario = new Usuario();
        usuario.setDireccion(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConTelefonoNulo() {
        Usuario usuario = new Usuario();
        usuario.setTelefono(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConRolNulo() {
        Usuario usuario = new Usuario();
        usuario.setRol(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarConMateriaNulo() {
        Usuario usuario = new Usuario();
        usuario.setMateria(null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        usuarioService.actualizar(1L, usuario);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }


    @Test
    public void testEliminarPorId() {
        usuarioService.eliminar(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    public void testBuscarFechaNacimiento() {
        List<Usuario> usuariosEsperados = Arrays.asList(new Usuario(1, "Juan"), new Usuario(2, "Pedro"));
        LocalDate fechaNacimiento = LocalDate.now();
        usuariosEsperados.get(0).setFechaNacimiento(fechaNacimiento);
        usuariosEsperados.get(1).setFechaNacimiento(fechaNacimiento);
        when(usuarioRepository.findAll()).thenReturn(usuariosEsperados);
        List<Usuario> usuariosObtenidos = usuarioService.buscarFechaNacimiento(null, fechaNacimiento);
        assertEquals(usuariosEsperados, usuariosObtenidos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuscarFechaNacimientoConFechaNacimientoNula() {

        usuarioService.buscarFechaNacimiento(1L, null);
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuscarFechaNacimientoConListaDeUsuariosNula() {
        when(usuarioRepository.findAll()).thenReturn(null);
        usuarioService.buscarFechaNacimiento(1L, LocalDate.now());
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuscarFechaNacimientoConListaDeUsuariosVacia() {
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        usuarioService.buscarFechaNacimiento(1L, LocalDate.now());
        fail("Se esperaba una excepción de IllegalArgumentException");

    }

    @Test
    public void testBuscarFechaNacimientoConId() {
        Usuario usuarioEsperado = new Usuario(1, "Juan");
        LocalDate fechaNacimiento = LocalDate.now();
        usuarioEsperado.setFechaNacimiento(fechaNacimiento);
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuarioEsperado));
        List<Usuario> usuariosObtenidos = usuarioService.buscarFechaNacimiento(1L, fechaNacimiento);
        assertEquals(Arrays.asList(usuarioEsperado), usuariosObtenidos);
    }

}

