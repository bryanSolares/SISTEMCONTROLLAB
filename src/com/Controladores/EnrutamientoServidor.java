package com.Controladores;

import com.DAO.Recursos.GestionarRecursos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class EnrutamientoServidor implements Serializable {

    private File archivo;
    private String usuario = "", contra = "", servidor = "", database = "";

    public EnrutamientoServidor(String usuario, String contra, String servidor, String database) {
        this.usuario = usuario;
        this.contra = contra;
        this.servidor = servidor;
        this.database = database;
        archivo = new File("ENRUTADOR.dll");
    }

    public EnrutamientoServidor() {
        archivo = new File("ENRUTADOR.dll");
    }

    public void crearArchivoEnrutador() {

        try (BufferedWriter escritorEnrutamiento = new BufferedWriter(new FileWriter(archivo))) {
            archivo.createNewFile();
            escritorEnrutamiento.write("[USUARIO]:" + usuario);
            escritorEnrutamiento.newLine();
            escritorEnrutamiento.write("[CONTRASENIA]:" + contra);
            escritorEnrutamiento.newLine();
            escritorEnrutamiento.write("[SERVIDOR]:" + servidor);
            escritorEnrutamiento.newLine();
            escritorEnrutamiento.write("[BASE DE DATOS]:" + database);
        } catch (Exception e) {
            GestionarRecursos.propagarError(e, "Error al crear Enrutador" + getClass());
        }

        System.out.println("Archivo creado exitosamente");
    }

    public void leerArchivoEnrutador() {
        String[] acceso = new String[4];
        int contador = 0;

        try (BufferedReader lectorEnrutamiento = new BufferedReader(new FileReader(archivo))) {

            while (lectorEnrutamiento.ready()) {
                acceso[contador] = lectorEnrutamiento.readLine();
                contador++;
            }

            usuario = acceso[0].substring(10, acceso[0].length());
            contra = acceso[1].substring(14, acceso[1].length());
            servidor = acceso[2].substring(11, acceso[2].length());
            database = acceso[3].substring(16, acceso[3].length());

//            JOptionPane.showMessageDialog(null, usuario);
//            JOptionPane.showMessageDialog(null, contra);
//            JOptionPane.showMessageDialog(null, servidor);
//            JOptionPane.showMessageDialog(null, database);

        } catch (Exception e) {
            GestionarRecursos.propagarError(e, "Error en leer Enrutador" + getClass());
        }

    }

    public File getArchivo() {
        return archivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
