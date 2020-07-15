package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOManager {

    DAOClientes getDAOClientes();

    DAOTareas getDAOTareas();
    
    DAOCapacitaciones getDAOCapacitaciones();

    boolean iniciarConexion(int tipoConexion) throws SQLException;

    String rutaImagenProyecto() throws Exception;

    Connection getConexion();
}
