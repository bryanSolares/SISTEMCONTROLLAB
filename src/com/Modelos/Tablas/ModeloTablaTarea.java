package com.Modelos.Tablas;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelo.Parametros;
import com.Modelo.Tarea;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModeloTablaTarea extends ModeloTabla<Tarea, DAOTareas> {

    private DAOClientes daoClientes;

    public ModeloTablaTarea(DAOTareas solicitaModelo, DAOClientes daoClientes) {
        super(solicitaModelo);
        this.daoClientes = daoClientes;
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarTodos();
        super.actualizarModelo();
    }

    @Override
    public int getRowCount() {
        return (int) listaDatos.stream().filter(e -> e.getEstado() == Parametros.PORHACER.getId()).count();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            Tarea tarea = listaDatos.stream().filter(e -> e.getEstado() == Parametros.PORHACER.getId()).collect(Collectors.toList()).get(rowIndex);
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

    public Tarea getElementById(Long id) {
        return listaDatos.stream().filter(e -> Objects.equals(id, e.getId())).findAny().get();
    }

}
