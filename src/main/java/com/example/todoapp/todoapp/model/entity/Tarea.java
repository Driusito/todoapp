package com.example.todoapp.todoapp.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private boolean completada;
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;
    // @ManyToOne
    // @JoinColumn(name = "usuario_id", nullable = false)
    // @JsonBackReference
    // private Usuario usuario;

    public Long getId() {
        return id;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

}
