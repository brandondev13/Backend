package com.brandon.reto.controller;

import com.brandon.reto.model.Tarea;
import com.brandon.reto.service.interfaces.TareaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TareaControllerTest {

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareaController tareaController;

    @Test
    public void testObtenerTodas() {
        List<Tarea> tareasEsperadas = Arrays.asList(new Tarea(1L, "Tarea 1"), new Tarea(2L, "Tarea 2"));
        when(tareaService.obtenerTodas()).thenReturn(tareasEsperadas);
        List<Tarea> tareasObtenidas = tareaController.obtenerTodas();
        assertEquals(tareasEsperadas, tareasObtenidas);
        verify(tareaService).obtenerTodas();
    }

    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Tarea tareaEsperada = new Tarea(id, "Tarea 1");
        when(tareaService.obtenerPorId(id)).thenReturn(tareaEsperada);
        Tarea tareaObtenida = tareaController.obtenerPorId(id);
        assertEquals(tareaEsperada, tareaObtenida);
        verify(tareaService).obtenerPorId(id);
    }

    @Test
    public void testCrear() {
        Tarea tarea = new Tarea(null, "Tarea 1");
        Tarea tareaEsperada = new Tarea(1L, "Tarea 1");
        when(tareaService.crear(tarea)).thenReturn(tareaEsperada);
        Tarea tareaObtenida = tareaController.crear(tarea);
        assertEquals(tareaEsperada, tareaObtenida);
        verify(tareaService).crear(tarea);
    }

    @Test
    public void testActualizar() {
        Long id = 1L;
        Tarea tarea = new Tarea(id, "Tarea 1 actualizada");
        Tarea tareaEsperada = new Tarea(id, "Tarea 1 actualizada");
        when(tareaService.actualizar(id, tarea)).thenReturn(tareaEsperada);
        Tarea tareaObtenida = tareaController.actualizar(id, tarea);
        assertEquals(tareaEsperada, tareaObtenida);
        verify(tareaService).actualizar(id, tarea);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;
        tareaController.eliminar(id);
        verify(tareaService).eliminar(id);
    }
}
