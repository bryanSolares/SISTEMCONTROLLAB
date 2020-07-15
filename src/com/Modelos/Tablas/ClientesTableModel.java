package com.Modelos.Tablas;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.modelo.Cliente;
import com.modelo.Parametros;

public class ClientesTableModel extends ModeloTabla<Cliente, DAOClientes> {

    
    public ClientesTableModel(DAOClientes solicitaModelo) {
        super(solicitaModelo);
    }

    @Override
    public void actualizarModelo() throws DAOException {
        datosTabla = solicitaModelo.buscarTodos();
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

}
