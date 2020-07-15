package com.DAO.Recursos;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class Animaciones extends MouseAdapter {

    private JComponent componente;
    private int tipoComponente = 0;

    private Color colorFondo;
    private String nombreTipografia;
    private int tamTipografia;
    private int estiloTipografia;


    public Animaciones(JComponent componente) {
        this.componente = componente;
        iniciarVariables();
    }

    public Animaciones(JComponent componente, int tipoComponente) {
        this.componente = componente;
        this.tipoComponente = tipoComponente;
        iniciarVariables();
    }

    private void iniciarVariables() {
        colorFondo = componente.getBackground();
        nombreTipografia = componente.getFont().getFontName();
        tamTipografia = componente.getFont().getSize();
        estiloTipografia = componente.getFont().getStyle();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        componente.setBackground(colorFondo.brighter());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        componente.setBackground(colorFondo);
    }

    public JComponent getComponente() {
        return componente;
    }

    public void setComponente(JComponent componente) {
        this.componente = componente;
    }

    public int getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(int tipoComponente) {
        this.tipoComponente = tipoComponente;
    }
}
