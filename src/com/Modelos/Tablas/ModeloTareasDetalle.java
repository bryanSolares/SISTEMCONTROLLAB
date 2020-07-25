package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.Modelo.Tarea;

public class ModeloTareasDetalle extends ModeloTabla<Tarea.TareaDetalle, DAOTareas> {

    private Long tarea;

    public ModeloTareasDetalle(DAOTareas solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarDetalles(tarea == null ? 0 : tarea);
        super.actualizarModelo();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarea.TareaDetalle detalle = datosTabla.values().stream().iterator().next().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return detalle.getIdDetalle();
            case 1:
                return detalle.getIdTarea();
            case 2:
                return detalle.getDescripcion();
            default:
                return "";
        }
    }
    
    public void setTarea(Long id){
        this.tarea = id;
    }

    @Override
    public Tarea.TareaDetalle getElementById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
