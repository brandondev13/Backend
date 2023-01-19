package com.brandon.reto.controller;

import com.brandon.reto.model.Usuario;
import com.brandon.reto.service.interfaces.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    public void testObtenerTodos() {
        List<Usuario> usuariosEsperados = Arrays.asList(new Usuario(1L, "Usuario 1"), new Usuario(2L, "Usuario 2"));
        when(usuarioService.obtenerTodos()).thenReturn(usuariosEsperados);
        List<Usuario> usuariosObtenidos = usuarioController.obtenerTodos();
        assertEquals(usuariosEsperados, usuariosObtenidos);
        verify(usuarioService).obtenerTodos();
    }

    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Usuario usuarioEsperado = new Usuario(id, "Usuario 1");
        when(usuarioService.obtenerPorId(id)).thenReturn(usuarioEsperado);
        Usuario usuarioObtenido = usuarioController.obtenerPorId(id);
        assertEquals(usuarioEsperado, usuarioObtenido);
        verify(usuarioService).obtenerPorId(id);
    }

    @Test
    public void testBuscarPorFecha() {
        Long id = 1L;
        LocalDate fechaNacimiento = LocalDate.of(2000, 01, 01);
        List<Usuario> usuariosEsperados = Arrays.asList(new Usuario(1L, "Usuario 1", fechaNacimiento));
        when(usuarioService.buscarFechaNacimiento(id, fechaNacimiento)).thenReturn(usuariosEsperados);
        List<Usuario> usuariosObtenidos = usuarioController.buscarPorFecha(id, fechaNacimiento);
        assertEquals(usuariosEsperados, usuariosObtenidos);
        verify(usuarioService).buscarFechaNacimiento(id, fechaNacimiento);
    }

    @Test
    public void testCrear() {
        Usuario usuario = new Usuario(null, "Usuario 1");
        Usuario usuarioEsperado = new Usuario(1L, "Usuario 1");
        when(usuarioService.crear(usuario)).thenReturn(usuarioEsperado);
        Usuario usuarioObtenido = usuarioController.crear(usuario);
        assertEquals(usuarioEsperado, usuarioObtenido);
        verify(usuarioService).crear(usuario);
    }

    @Test
    public void testActualizar() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Usuario 1 actualizado");
        Usuario usuarioEsperado = new Usuario(id, "Usuario 1 actualizado");
        when(usuarioService.actualizar(id, usuario)).thenReturn(usuarioEsperado);
        Usuario usuarioObtenido = usuarioController.actualizar(id, usuario);
        assertEquals(usuarioEsperado, usuarioObtenido);
        verify(usuarioService).actualizar(id, usuario);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;
        usuarioController.eliminar(id);
        verify(usuarioService).eliminar(id);
    }
}
