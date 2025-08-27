package com.example.todoapp.todoapp.service;

import java.util.List;
import java.util.Optional;


import com.example.todoapp.todoapp.model.entity.Tarea;

public interface TareaService {   
    
    List<Tarea> getAllTareas();
    
    Optional<Tarea> getTareaById(Long id);
    
    Tarea createTarea(Tarea tarea);

    void setCompletada(Long id, boolean completada);
    
    void deleteTarea(Long id);
    
    List<Tarea> findByTitulo(String titulo);
    
    List<Tarea> findByCompletada(boolean completada);

    Tarea editarTarea(Long id, Tarea tarea);

}
