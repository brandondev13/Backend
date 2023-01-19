package com.brandon.reto.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.brandon.reto.model.Tarea;
import com.brandon.reto.model.Usuario;
import com.brandon.reto.repository.TareaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaService tareaService;

    @Test
    public void testObtenerTodas() {
        List<Tarea> tareasEsperadas = Arrays.asList(new Tarea(1L, "Tarea 1"), new Tarea(2L, "Tarea 2"));
        when(tareaRepository.findAll()).thenReturn(tareasEsperadas);
        List<Tarea> tareasObtenidas = tareaService.obtenerTodas();
        assertEquals(tareasEsperadas, tareasObtenidas);
    }

    @Test
    public void testObtenerPorId() {
        Tarea tareaEsperada = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaEsperada));
        Tarea tareaObtenida = tareaService.obtenerPorId(1L);
        assertEquals(tareaEsperada, tareaObtenida);
    }

    @Test
    public void testObtenerPorIdNoExiste() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.empty());
        Tarea tareaObtenida = tareaService.obtenerPorId(1L);
        assertNull(tareaObtenida);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCrear() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setUsuarioAsignado(new Usuario(1L, "Juan"));
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaInicio(LocalDate.now());
        tarea.setFechaFinalizacion(LocalDate.now().plusDays(1));
        Tarea tareaCreada = tareaService.crear(tarea);
        assertEquals(tarea, tareaCreada);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearNombreNulo() {
        Tarea tarea = new Tarea(1L, null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearEstadoNulo() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setEstado(null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearUsuarioAsignadoNulo() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setUsuarioAsignado((Usuario) null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearFechaCreacionNula() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setFechaCreacion(null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearFechaInicioNula() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setFechaInicio(null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearFechaFinalizacionNula() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setFechaFinalizacion(null);
        tareaService.crear(tarea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearFechaFinalizacionAnteriorAFechaInicio() {
        Tarea tarea = new Tarea(1L, "Tarea 1");
        tarea.setFechaInicio(LocalDate.now());
        tarea.setFechaFinalizacion(tarea.getFechaInicio().minusDays(1));
        tareaService.crear(tarea);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testActualizar() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setUsuarioAsignado(new Usuario(2L, "Maria"));
        Tarea tareaObtenida = tareaService.actualizar(1L, tareaActualizada);
        assertEquals(tareaActualizada, tareaObtenida);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testActualizarTareaNoExiste() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.empty());
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaService.actualizar(1L, tareaActualizada);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testActualizarNombreNulo() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarEstadoNulo() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setEstado(null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarUsuarioAsignadoNulo() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setUsuarioAsignado((Usuario) null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarFechaCreacionNula() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setFechaCreacion(null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarFechaInicioNula() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setFechaInicio(null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarFechaFinalizacionNula() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setFechaFinalizacion(null);
        tareaService.actualizar(1L, tareaActualizada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarFechaFinalizacionAnteriorAFechaInicio() {
        Tarea tareaExistente = new Tarea(1L, "Tarea 1");
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        Tarea tareaActualizada = new Tarea(1L, "Tarea Actualizada");
        tareaActualizada.setFechaInicio(LocalDate.now());
        tareaActualizada.setFechaFinalizacion(tareaActualizada.getFechaInicio().minusDays(1));
        tareaService.actualizar(1L, tareaActualizada);
    }


    @Test
    public void testEliminar() {
        tareaService.eliminar(1L);
        verify(tareaRepository).deleteById(1L);
    }


}

