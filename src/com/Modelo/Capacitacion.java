package com.Modelo;

import com.modelo.Parametros;
import com.modelo.Cliente;
import java.time.LocalDate;
import java.util.List;

public class Capacitacion {

    private Long idCapacitacion;
    private Cliente idCliente;
    private Parametros idSistema;
    private LocalDate fecha_fin;
    private Parametros responsable;
    private Integer estadoActual;
    private Parametros tipoCapacitacion;
    private List<CapacitacionDetalle> detalleModulos;

    public Capacitacion() {
    }

    public Capacitacion(Cliente idCliente, Parametros idSistema, Parametros responsable, Integer estadoActual, Parametros tipoCapacitacion) {
        this.idCliente = idCliente;
        this.idSistema = idSistema;
        this.responsable = responsable;
        this.estadoActual = estadoActual;
        this.tipoCapacitacion = tipoCapacitacion;
    }

    public class CapacitacionDetalle {

        private Capacitacion idCapacitacion;
        private Parametros codModulo;
        private String descripcionModulo;
        private LocalDate fecha_inicio, fecha_fin;
        private Parametros estado;
        
        public CapacitacionDetalle() {
        }

        public CapacitacionDetalle(Parametros codModulo, String descripcionModulo, Parametros estado) {
            this.codModulo = codModulo;
            this.descripcionModulo = descripcionModulo;
            this.estado = estado;
        }

        public Capacitacion getIdCapacitacion() {
            return idCapacitacion;
        }

        public void setIdCapacitacion(Capacitacion idCapacitacion) {
            this.idCapacitacion = idCapacitacion;
        }

        public Parametros getCodModulo() {
            return codModulo;
        }

        public void setCodModulo(Parametros codModulo) {
            this.codModulo = codModulo;
        }

        public String getDescripcionModulo() {
            return descripcionModulo;
        }

        public void setDescripcionModulo(String descripcionModulo) {
            this.descripcionModulo = descripcionModulo;
        }

        public LocalDate getFecha_inicio() {
            return fecha_inicio;
        }

        public void setFecha_inicio(LocalDate fecha_inicio) {
            this.fecha_inicio = fecha_inicio;
        }

        public LocalDate getFecha_fin() {
            return fecha_fin;
        }

        public void setFecha_fin(LocalDate fecha_fin) {
            this.fecha_fin = fecha_fin;
        }

        public Parametros getEstado() {
            return estado;
        }

        public void setEstado(Parametros estado) {
            this.estado = estado;
        }
        
        

        @Override
        public String toString() {
            return "CapacitacionDetalle{" + "idCapacitacion=" + idCapacitacion + ", codModulo=" + codModulo + ", descripcionModulo=" + descripcionModulo + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", estado=" + estado + '}';
        }

    }

    public Long getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Long idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Parametros getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Parametros idSistema) {
        this.idSistema = idSistema;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Parametros getResponsable() {
        return responsable;
    }

    public void setResponsable(Parametros responsable) {
        this.responsable = responsable;
    }

    public Integer getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Integer estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Parametros getTipoCapacitacion() {
        return tipoCapacitacion;
    }

    public void setTipoCapacitacion(Parametros tipoCapacitacion) {
        this.tipoCapacitacion = tipoCapacitacion;
    }

    public List<CapacitacionDetalle> getDetalleModulos() {
        return detalleModulos;
    }

    public void setDetalleModulos(List<CapacitacionDetalle> detalleModulos) {
        this.detalleModulos = detalleModulos;
    }

    
    
    @Override
    public String toString() {
        return "Capacitacion{" + "idCapacitacion=" + idCapacitacion + ", idCliente=" + idCliente + ", idSistema=" + idSistema + ", fecha_fin=" + fecha_fin + ", responsable=" + responsable + ", estadoActual=" + estadoActual + ", tipoCapacitacion=" + tipoCapacitacion + ", detalleModulos=" + detalleModulos + '}';
    }

}
