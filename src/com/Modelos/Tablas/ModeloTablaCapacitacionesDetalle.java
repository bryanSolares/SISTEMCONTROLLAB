package com.Modelos.Tablas;

import com.DAO.DAOCapacitaciones;
import com.DAO.DAOException;
import com.Modelo.Capacitacion;
import java.time.LocalDate;
import java.util.List;

public class ModeloTablaCapacitacionesDetalle extends ModeloTabla<Capacitacion.CapacitacionDetalle, DAOCapacitaciones> {

    private Capacitacion capacitacion;
    private List<Capacitacion.CapacitacionDetalle> listaDetalleCapacitacion;

    public ModeloTablaCapacitacionesDetalle(DAOCapacitaciones solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarModulosRelacionados(capacitacion);
        listaDetalleCapacitacion = datosTabla.values().iterator().next();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Capacitacion.CapacitacionDetalle detalle = datosTabla.values().iterator().next().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return capacitacion.getIdCapacitacion();
            case 1:
                return detalle.getCodModulo().getId();
            case 2:
                return detalle.getDescripcionModulo();
            case 3:
                return detalle.getFecha_inicio() != LocalDate.MIN ? detalle.getFecha_inicio() : "";
            case 4:
                return detalle.getFecha_fin() != LocalDate.MIN ? detalle.getFecha_fin() : "";
            case 5:
                return detalle.getEstado().toString();
            default:
                return "";
        }

    }

    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }

    public List<Capacitacion.CapacitacionDetalle> getListaDetalleCapacitacion() {
        return listaDetalleCapacitacion;
    }

    @Override
    public Capacitacion.CapacitacionDetalle getElementById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
