package com.example.todoapp.todoapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.todoapp.todoapp.model.entity.Tarea;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long> {
    
     List<Tarea> findByTitulo(String titulo);
     List<Tarea> findByCompletada(boolean completada);

}
