package com.brandon.reto.controller;

import com.brandon.reto.model.Usuario;
import com.brandon.reto.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "API para manejar usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios existentes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"), @ApiResponse(responseCode = "500", description = "Error al obtener la lista de usuarios")})
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @Operation(summary = "Obtener usuario por id", description = "Devuelve un usuario dado su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @Operation(summary = "Buscar usuarios por fecha de nacimiento", description = "Devuelve una lista de usuarios que cumplen con una fecha de nacimiento específica")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"), @ApiResponse(responseCode = "404", description = "No se encontraron usuarios con la fecha especificada"), @ApiResponse(responseCode = "500", description = "Error al obtener la lista de usuarios")})
    @GetMapping("/buscarporfecha")
    public List<Usuario> buscarPorFecha(@Parameter(description = "Id del usuario a buscar (opcional)") @RequestParam(required = false) Long id, @Parameter(description = "Fecha de nacimiento del usuario a buscar") @RequestParam(required = false) LocalDate fechaNacimiento) {
        return usuarioService.buscarFechaNacimiento(id, fechaNacimiento);
    }

    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"), @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos"), @ApiResponse(responseCode = "500", description = "Error al crear el usuario")})
    @PostMapping
    public Usuario crear(@Parameter(description = "Datos del usuario a crear") @RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario dado su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado"), @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos"), @ApiResponse(responseCode = "500", description = "Error al actualizar el usuario")})
    @PutMapping("/{id}")
    public Usuario actualizar(@Parameter(description = "Id del usuario a actualizar") @PathVariable Long id, @Parameter(description = "Datos del usuario a actualizar") @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario dado su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado"), @ApiResponse(responseCode = "500", description = "Error al eliminar el usuario")})
    @DeleteMapping("/{id}")
    public void eliminar(@Parameter(description = "Id del usuario a eliminar") @PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}

