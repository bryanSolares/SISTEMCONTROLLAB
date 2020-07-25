package com.Modelos.Tablas;

import com.DAO.DAOCapacitaciones;
import com.DAO.DAOException;
import com.Modelo.Capacitacion;
import java.time.LocalDate;
import java.util.List;

public class ModeloTablaCapacitaciones extends ModeloTabla<Capacitacion, DAOCapacitaciones> {

    private List<Capacitacion> listaDetalleCapacitaciones;
    
    public ModeloTablaCapacitaciones(DAOCapacitaciones solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarTodos();
        listaDetalleCapacitaciones = datosTabla.values().iterator().next();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Capacitacion capacitacion = datosTabla.values().iterator().next().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return capacitacion.getIdCapacitacion();
            case 1:
                return capacitacion.getIdCliente().getNombre();
            case 2:
                return capacitacion.getIdSistema().toString();
            case 3:
                return capacitacion.getFecha_fin() != LocalDate.MIN ? capacitacion.getFecha_fin() : "";
            case 4:
                return capacitacion.getResponsable().toString();
            case 5:
                return capacitacion.getEstadoActual();
            case 6:
                return capacitacion.getTipoCapacitacion().toString();
            default:
                return "";
        }
    }

    public List<Capacitacion> getListaDetalleCapacitaciones() {
        return listaDetalleCapacitaciones;
    }

    @Override
    public Capacitacion getElementById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
