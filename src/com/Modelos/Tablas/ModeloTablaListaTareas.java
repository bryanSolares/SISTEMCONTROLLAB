package com.Modelos.Tablas;

import com.DAO.DAOClientes;
import com.DAO.DAOTareas;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelo.Parametros;
import com.Modelo.Tarea;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModeloTablaListaTareas extends ModeloTabla<Tarea, DAOTareas> {

    private Long id_cliente;
    private LocalDate fecha_ini;
    private LocalDate fecha_fin;
    private List<Tarea> lista = new ArrayList<>();
    private DAOClientes daoClientes;

    public ModeloTablaListaTareas(DAOTareas solicitaModelo, DAOClientes daoClientes) {
        super(solicitaModelo);
        this.daoClientes = daoClientes;
        id_cliente = null;
        fecha_ini = null;
        fecha_fin = null;
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.busquedaConFiltros(id_cliente, fecha_ini, fecha_fin);
        super.actualizarModelo();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            Tarea tarea = listaDatos.get(rowIndex);
            DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            switch (columnIndex) {
                case 0:
                    return tarea.getId();
                case 1:
                    return tarea.getTitulo();
                case 2:
                    return daoClientes.buscarUno(tarea.getCliente()).getNombre(); //mejorar rendimiento
                case 3:
                    return tarea.getDescripcion();
                case 4:
                    return (String) Parametros.devuelveValorParametro(tarea.getPrioridad(), Parametros.DEVUELVE_VALOR);
                case 5:
                    return (String) Parametros.devuelveValorParametro(tarea.getEstado(), Parametros.DEVUELVE_VALOR);
                case 6:
                    return tarea.getDuracionTarea();
                case 7:
                    return formatoFechas.format(tarea.getFechaInicio());
                case 8:
                    if (tarea.getEstado() == Parametros.FINAL.getId()) {
                        return formatoFechas.format(tarea.getFechaFin());
                    } else {
                        return "";
                    }
                case 9:
                    return (String) Parametros.devuelveValorParametro(tarea.getAreaResponsable(), Parametros.DEVUELVE_VALOR);
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error en llamada de datos a TABLA en: " + this.getClass());
        }
        return "";

    }

    public void estableFiltros(Long cliente, LocalDate fecha_ini, LocalDate fecha_fin) {
        id_cliente = cliente;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
    }

    @Override
    public Tarea getElementById(Long id) {
        return this.listaDatos.stream().filter(e -> e.getId().equals(id)).findFirst().get();
    }

}
