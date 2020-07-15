package com.test;

import com.Controladores.EnrutamientoServidor;
import com.Controladores.SQLDAOManager;
import com.DAO.DAOManager;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Graficos.Login.CrearDireccionamiento;
import com.Graficos.Login.Logeo;
import com.Graficos.Login.PantallaPrincipal;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestSistema {

    public static void main(String[] args) throws SQLException, DAOException {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    GestionarRecursos.propagarError(ex, "Error en Nimbus");
                }
            }
        }

        int tamx = Toolkit.getDefaultToolkit().getScreenSize().width;
        int tamy = Toolkit.getDefaultToolkit().getScreenSize().height;

        EnrutamientoServidor enrutamiento = new EnrutamientoServidor();
        DAOManager manager = new SQLDAOManager(enrutamiento);
        CrearDireccionamiento creador;
        Logeo ingreso;


        PantallaPrincipal principal = new PantallaPrincipal();
        principal.setSize(new Dimension(tamx, tamy - 50));
        principal.setLocation(0, 0);
        principal.setVisible(true);

        if (!enrutamiento.getArchivo().exists()) {
            creador = new CrearDireccionamiento(principal, true);
            creador.setLocationRelativeTo(null);
            creador.setVisible(true);
        } else {
            
            ingreso = new Logeo(principal, true, enrutamiento, manager);
            ingreso.setLocationRelativeTo(null);
            ingreso.setVisible(true);
        }
    }
}
