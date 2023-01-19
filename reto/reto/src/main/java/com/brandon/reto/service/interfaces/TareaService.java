package com.brandon.reto.service.interfaces;

import com.brandon.reto.model.Tarea;
import java.util.List;

public interface TareaService {
    List<Tarea> obtenerTodas();
    Tarea obtenerPorId(Long id);
    Tarea crear(Tarea tarea);
    Tarea actualizar(Long id, Tarea tarea);
    void eliminar(Long id);
}
