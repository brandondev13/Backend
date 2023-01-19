package com.brandon.reto.service;

import com.brandon.reto.model.Tarea;
import com.brandon.reto.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TareaService implements com.brandon.reto.service.interfaces.TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea obtenerPorId(Long id) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        return tarea.orElse(null);
    }

    @Override
    public Tarea crear(Tarea tarea) {

        if (tarea.getNombre() == null || tarea.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea es obligatorio");
        }
        if (tarea.getEstado() == null || tarea.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado de la tarea es obligatorio");
        }
        if (tarea.getUsuarioAsignado() == null) {
            throw new IllegalArgumentException("El usuario asignado de la tarea es obligatorio");
        }
        if (tarea.getFechaCreacion() == null) {
            throw new IllegalArgumentException("La fecha de creación de la tarea es obligatoria");
        }
        if (tarea.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio de la tarea es obligatoria");
        }
        if (tarea.getFechaFinalizacion() == null) {
            throw new IllegalArgumentException("La fecha de finalización de la tarea es obligatoria");
        }
        if (tarea.getFechaFinalizacion().isBefore(tarea.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de finalización de la tarea no puede ser anterior a la fecha de inicio");
        }
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea actualizar(Long id, Tarea tarea) {
        Optional<Tarea> tareaExistente = tareaRepository.findById(id);
        if (tareaExistente.isPresent()) {
            if (tarea.getNombre() == null || tarea.getNombre().isEmpty()) {
                throw new IllegalArgumentException("El nombre de la tarea es obligatorio");
            }
            if (tarea.getEstado() == null || tarea.getEstado().isEmpty()) {
                throw new IllegalArgumentException("El estado de la tarea es obligatorio");
            }
            if (tarea.getUsuarioAsignado() == null) {
                throw new IllegalArgumentException("El usuario asignado de la tarea es obligatorio");
            }
            if (tarea.getFechaCreacion() == null) {
                throw new IllegalArgumentException("La fecha de creación de la tarea es obligatoria");
            }
            if (tarea.getFechaInicio() == null) {
                throw new IllegalArgumentException("La fecha de inicio de la tarea es obligatoria");
            }
            if (tarea.getFechaFinalizacion() == null) {
                throw new IllegalArgumentException("La fecha de finalización de la tarea es obligatoria");
            }
            if (tarea.getFechaFinalizacion().isBefore(tarea.getFechaInicio())) {
                throw new IllegalArgumentException("La fecha de finalización de la tarea no puede ser anterior a la fecha de inicio");
            }
            tareaExistente.get().setNombre(tarea.getNombre());
            tareaExistente.get().setEstado(tarea.getEstado());
            tareaExistente.get().setUsuarioAsignado(tarea.getUsuarioAsignado());
            tareaExistente.get().setFechaCreacion(tarea.getFechaCreacion());
            tareaExistente.get().setFechaInicio(tarea.getFechaInicio());
            tareaExistente.get().setFechaFinalizacion(tarea.getFechaFinalizacion());
            return tareaRepository.save(tareaExistente.get());
        } else {
            throw new IllegalArgumentException("La tarea con id " + id + " no existe");
        }
    }

    @Override
    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
