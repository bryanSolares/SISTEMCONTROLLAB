package com.DAO.Recursos;

import com.Modelo.Parametros;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CheckPersonalizado extends JCheckBox implements ListCellRenderer<CheckPersonalizado> {

    private final Parametros descripcionCheck;
    private boolean estadoSeleccion = false;

    public CheckPersonalizado() {
        this.descripcionCheck = null;
    }

    public CheckPersonalizado(Parametros descripcionCheck) {
        this.descripcionCheck = descripcionCheck;
    }

    public boolean getEstadoSeleccion() {
        return estadoSeleccion;
    }

    public void estableceEstado(boolean estadoSeleccion) {
        this.estadoSeleccion = estadoSeleccion;
    }

    @Override
    public String toString() {
        return descripcionCheck.toString();
    }

    public Parametros getDescripcionCheck() {
        return descripcionCheck;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CheckPersonalizado> list, CheckPersonalizado value, int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(value.getEstadoSeleccion());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }

    public class AccionCheck extends MouseAdapter {

        List<CheckPersonalizado> lista = new ArrayList<>();
        JList<CheckPersonalizado> listachecks;

        public AccionCheck() {
        }

        public AccionCheck(JList<CheckPersonalizado> listachecks) {
            this.listachecks = listachecks;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            int localizacionClick = listachecks.locationToIndex(e.getPoint());
            CheckPersonalizado seleccion = (CheckPersonalizado) listachecks.getModel().getElementAt(localizacionClick);
            seleccion.estableceEstado(!seleccion.getEstadoSeleccion());
            listachecks.repaint(listachecks.getCellBounds(localizacionClick, localizacionClick));

            if (!lista.contains(seleccion)) {
                lista.add(seleccion);
            } else {
                lista.remove(seleccion);
            }
        }

        public List<CheckPersonalizado> getLista() {
            return lista;
        }
        
        public void limpiarLista(){
            lista.clear();
        }

        public void setLista(List<CheckPersonalizado> lista) {
            this.lista = lista;
        }

        public void setContenedorModulos(JList<CheckPersonalizado> contenedor) {
            this.listachecks = contenedor;
        }

    }
}
