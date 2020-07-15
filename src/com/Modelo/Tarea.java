package com.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tarea {

    private Long id;
    private String titulo;
    private Long cliente;
    private String descripcion;
    private int prioridad;
    private int estado;
    private String duracionTarea; // cambiar a string - cambiar procedimiento en crear y editar tarea
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int AreaResponsable;
    private List<TareaDetalle> listaDetalles;

    public Tarea() {
        this.duracionTarea = "00:00:00:00";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        listaDetalles = new ArrayList<>();
    }

    public Tarea(String titulo, Long cliente, int prioridad, int estado) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.prioridad = prioridad;
        this.estado = estado;
        this.duracionTarea = "00:00:00:00";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        listaDetalles = new ArrayList<>();
    }

    public class TareaDetalle {

        private int idDetalle;
        private int idTarea;
        private String descripcion;

        public TareaDetalle() {
            this.idDetalle = 0;
            this.idTarea = 0;
            this.descripcion = "";
        }

        public TareaDetalle(int idDetalle, int idTarea, String descripcion) {
            this.idDetalle = idDetalle;
            this.idTarea = idTarea;
            this.descripcion = descripcion;
        }

        public int getIdDetalle() {
            return idDetalle;
        }

        public void setIdDetalle(int idDetalle) {
            this.idDetalle = idDetalle;
        }

        public int getIdTarea() {
            return idTarea;
        }

        public void setIdTarea(int idTarea) {
            this.idTarea = idTarea;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + this.idDetalle;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TareaDetalle other = (TareaDetalle) obj;
            if (this.idDetalle != other.idDetalle) {
                return false;
            }
            return true;
        }
        
        

        @Override
        public String toString() {
            return "TareaDetalle{" + "idDetalle=" + idDetalle + ", idTarea=" + idTarea + ", descripcion=" + descripcion + '}';
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracionTarea() {
        return duracionTarea;
    }

    public void setDuracionTarea(String duracionTarea) {
        this.duracionTarea = duracionTarea;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getAreaResponsable() {
        return AreaResponsable;
    }

    public void setAreaResponsable(int AreaResponsable) {
        this.AreaResponsable = AreaResponsable;
    }

    public List<TareaDetalle> getListaDetalles() {
        return listaDetalles;
    }

    public void agregarADetalle(int idDetalle, int idTarea, String descripcion) {
        listaDetalles.add(new TareaDetalle(idDetalle, idTarea, descripcion));
    }

    public void setListaDetalles(List<TareaDetalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarea other = (Tarea) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Tarea{" + "id=" + id + ", titulo=" + titulo + ", proveedor=" + cliente + ", descripcion=" + descripcion + ", prioridad=" + prioridad + ", estado=" + estado + ", duracionTarea=" + duracionTarea + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }

}
