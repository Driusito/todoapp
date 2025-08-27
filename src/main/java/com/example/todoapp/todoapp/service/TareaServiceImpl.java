package com.example.todoapp.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.todoapp.model.entity.Tarea;
import com.example.todoapp.todoapp.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> getAllTareas() {
        return (List<Tarea>) tareaRepository.findAll();       
    }

    @Override
    public Optional<Tarea> getTareaById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        if (tarea.getTitulo() == null || tarea.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título de la tarea no puede estar vacío");
        }
        return tareaRepository.save(tarea);
    }

    @Override
    public void setCompletada(Long id, boolean completada) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(completada);
            tareaRepository.save(tarea);
        } else {
            throw new IllegalArgumentException("Tarea con ID " + id + " no existe");
        }
    }

    @Override
    public void deleteTarea(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarea con ID " + id + " no existe");
        }
        tareaRepository.deleteById(id);
    }

    @Override
    public List<Tarea> findByTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return tareaRepository.findByTitulo(titulo);
    }

    @Override
    public List<Tarea> findByCompletada(boolean completada) {
        return tareaRepository.findByCompletada(completada);
    }

    public Tarea editarTarea(Long id, Tarea tareaNueva) {
        return tareaRepository.findById(id).map(tarea -> {
            tarea.setTitulo(tareaNueva.getTitulo());
            tarea.setDescripcion(tareaNueva.getDescripcion());
            tarea.setCompletada(tareaNueva.isCompletada());
            return tareaRepository.save(tarea);
        }).orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
    }
    
}
