package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.modelo.Tarea;

public class ModeloTablaTarea extends ModeloTabla<Tarea, DAOTareas> {

    public ModeloTablaTarea() {
        super(null);
    }

    public ModeloTablaTarea(DAOTareas solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarTodos();
        super.actualizarModelo();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarea tarea = datosTabla.values().stream().iterator().next().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tarea.getId();
            case 1:
                return tarea.getDescripcion();
            case 2:
                return "";//return daoClientes.buscarUno(tarea.getCliente()).getNombre();
            case 3:
                return tarea.getFechaInicio();
            default:
                return "";
        }
    }

}
