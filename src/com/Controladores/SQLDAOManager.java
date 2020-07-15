package com.Controladores;

import com.DAO.DAOCapacitaciones;
import com.DAO.DAOClientes;
import com.DAO.DAOManager;
import com.DAO.DAOTareas;
import com.DAO.SQLCRUD.CRUDSQLCapacitaciones;
import com.DAO.SQLCRUD.CRUDSQLClientes;
import com.DAO.SQLCRUD.CRUDSQLTareas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLDAOManager implements DAOManager {

    private Connection conexion = null;
    private DAOClientes DAO_Clientes = null;
    private DAOTareas DAO_Tarea = null;
    private DAOCapacitaciones DAO_Capacitaciones = null;
    private final EnrutamientoServidor enrutamiento;
    private final static int SQL_SERVER = 1, MYSQL = 2;

    public SQLDAOManager(EnrutamientoServidor enrutamiento) {
        this.enrutamiento = enrutamiento;
    }

    @Override
    public boolean iniciarConexion(int tipoConexion) throws SQLException {
        try {

            switch (tipoConexion) {
                case SQL_SERVER:
                    conexion = DriverManager.getConnection("jdbc:sqlserver://" + enrutamiento.getServidor()
                            + ";databaseNAME=" + enrutamiento.getDatabase() + ";", enrutamiento.getUsuario(), enrutamiento.getContra());
                    return true;
                case MYSQL:
                    conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + enrutamiento.getDatabase() + "?useSSL=false", enrutamiento.getUsuario(), enrutamiento.getContra());
                    return true;

            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Al parecer se ha perdido la conexión", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public DAOClientes getDAOClientes() {
        if (DAO_Clientes == null) {
            DAO_Clientes = new CRUDSQLClientes(conexion);
        }
        return DAO_Clientes;

    }

    @Override
    public DAOTareas getDAOTareas() {
        if (DAO_Tarea == null) {
            DAO_Tarea = new CRUDSQLTareas(conexion);
        }
        return DAO_Tarea;
    }

    @Override
    public DAOCapacitaciones getDAOCapacitaciones() {
        if (DAO_Capacitaciones == null) {
            DAO_Capacitaciones = new CRUDSQLCapacitaciones(conexion);
        }
        return DAO_Capacitaciones;
    }

    @Override
    public Connection getConexion() {
        return conexion;
    }

    @Override
    public String rutaImagenProyecto() throws Exception {
        return "/com/Iconos/imagen-proyecto.png";
    }

}
