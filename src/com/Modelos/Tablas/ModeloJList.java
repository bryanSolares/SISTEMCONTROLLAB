package com.Modelos.Tablas;

import com.DAO.Recursos.CheckPersonalizado;
import java.util.List;
import javax.swing.DefaultListModel;

public class ModeloJList extends DefaultListModel<CheckPersonalizado> {

    List<CheckPersonalizado> listaModulos;

    public ModeloJList() {
    }

    public ModeloJList(List<CheckPersonalizado> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public void cargarLista() {
        removeAllElements();
        if (listaModulos != null) {
            listaModulos.forEach(elemento -> {
                addElement(elemento);
            });
        }
    }
   
    
    
    public List<CheckPersonalizado> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<CheckPersonalizado> listaModulos) {
        this.listaModulos = listaModulos;
    }

}
