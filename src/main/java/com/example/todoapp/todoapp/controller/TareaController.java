package com.example.todoapp.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.todoapp.todoapp.model.entity.Tarea;
import com.example.todoapp.todoapp.service.TareaService;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {

    @Autowired
    TareaService tareaService;

    @GetMapping("/all")
    public List<Tarea> getAllTareas() {
        return tareaService.getAllTareas();
    }

    @PostMapping("/nueva")
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaService.createTarea(tarea);
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteTarea(@PathVariable Long id) {
        tareaService.deleteTarea(id);
    }

    @PutMapping("/completada/{id}")
    public void setCompletada(@PathVariable Long id, @RequestParam boolean completada) {
        tareaService.setCompletada(id, completada);
    }

    @PutMapping("/editar/{id}")
    public Tarea editarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaService.editarTarea(id, tarea);
    }
}
