package com.Modelos.Tablas;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.Modelo.Cliente;
import com.Modelo.Parametros;

public class ModeloTablaClientes extends ModeloTabla<Cliente,DAOClientes> {

    
    public ModeloTablaClientes(DAOClientes solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarTodos();
        super.actualizarModelo();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = datosTabla.values().stream().iterator().next().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNombre();
            case 2:
                return (String)Parametros.devuelveValorParametro(cliente.getEstado(), Parametros.DEVUELVE_VALOR);
            case 3:
                return cliente.getTelefono();
            case 4:
                return (String)Parametros.devuelveValorParametro(cliente.gettCliente(), Parametros.DEVUELVE_VALOR);
            case 5:
                return (String)Parametros.devuelveValorParametro(cliente.getId_sistema(), Parametros.DEVUELVE_VALOR);
            case 6:
                return cliente.getContacto();
            default:
                return "";
        }
    }

    @Override
    public Cliente getElementById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
