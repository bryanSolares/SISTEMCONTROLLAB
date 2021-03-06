package com.Modelos.Combos;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.modelo.Cliente;
import com.modelo.Parametros;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

public class ClientesComboModel extends DefaultComboBoxModel<Cliente>{

    private DAOClientes cliente;
    private Map<ResultSetMetaData, List<Cliente>> mapeoClientes = new HashMap<>();
    private boolean filtraClienteActivos = false;

    public ClientesComboModel(DAOClientes cliente) {
        this.cliente = cliente;
    }

    public void actualizarModelo() throws DAOException {
        if (mapeoClientes != null) {
            removeAllElements();
            mapeoClientes = cliente.buscarTodos();
            if (filtraClienteActivos) {
                mapeoClientes.values().stream().iterator().next()
                        .stream()
                        .filter(e -> e.getEstado() != Parametros.INACTIVO.getId())
                        .sorted(Cliente::compareTo)
                        .forEach(e -> {
                            addElement(e);
                        });
            } else {
                addElement(null);
                mapeoClientes.values().stream().iterator().next()
                        .stream()
                        .sorted(Cliente::compareTo)
                        .forEach(e -> {
                            addElement(e);
                        });
            }
        }
    }
    
    public void filtraClienteActivos(boolean filtraClientesActivos) {
        this.filtraClienteActivos = filtraClientesActivos;
    }

}
