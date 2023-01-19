package com.brandon.reto.controller;

import com.brandon.reto.model.Tarea;
import com.brandon.reto.service.interfaces.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarea")
@Tag(name = "Tarea", description = "API para manejar tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista de todas las tareas existentes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de tareas obtenida exitosamente"), @ApiResponse(responseCode = "500", description = "Error al obtener la lista de tareas")})
    @GetMapping
    public List<Tarea> obtenerTodas() {
        return tareaService.obtenerTodas();
    }

    @Operation(summary = "Obtener tarea por id", description = "Devuelve una tarea dado su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tarea obtenida exitosamente"), @ApiResponse(responseCode = "404", description = "Tarea no encontrada"), @ApiResponse(responseCode = "500", description = "Error al obtener la tarea")})
    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable @Parameter(description = "Id de la tarea a obtener") Long id) {
        return tareaService.obtenerPorId(id);
    }

    @Operation(summary = "Crear tarea", description = "Crea una nueva tarea")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tarea creada exitosamente"), @ApiResponse(responseCode = "400", description = "Datos de la tarea inválidos"), @ApiResponse(responseCode = "500", description = "Error al crear la tarea")})
    @PostMapping
    public Tarea crear(@RequestBody @Parameter(description = "Datos de la tarea a crear") Tarea tarea) {
        return tareaService.crear(tarea);
    }

    @Operation(summary = "Actualizar tarea", description = "Actualiza una tarea existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente"), @ApiResponse(responseCode = "404", description = "Tarea no encontrada"), @ApiResponse(responseCode = "400", description = "Datos de la tarea inválidos"), @ApiResponse(responseCode = "500", description = "Error al actualizar la tarea")})
    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable @Parameter(description = "Id de la tarea a actualizar") Long id, @RequestBody @Parameter(description = "Datos de la tarea a actualizar") Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    @Operation(summary = "Eliminar tarea", description = "Elimina una tarea existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente"), @ApiResponse(responseCode = "404", description = "Tarea no encontrada"), @ApiResponse(responseCode = "500", description = "Error al eliminar la tarea")})
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @Parameter(description = "Id de la tarea a eliminar") Long id) {
        tareaService.eliminar(id);
    }
}