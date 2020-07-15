package com.DAO.Recursos;

import java.util.List;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

public class Validaciones {

    private final List<JTextComponent> lista;
    private final Component componentePadre;
    private final List<JComboBox> listaCombo;
    private final List<JCheckBox> listaChecks;
    private final List<TableModel> listaTablas;

    public final static int CAMPOS_VALIDADO = 1;
    public final static int COMBOS_VALIDADOS = 2;
    public final static int CHECKS_VALIDADOS = 3;
    public final static int TABLAS_VALIDADAS = 4;

    public Validaciones(Component componentePadre) {
        this.componentePadre = componentePadre;
        lista = new ArrayList<>();
        listaCombo = new ArrayList<>();
        listaChecks = new ArrayList<>();
        listaTablas = new ArrayList<>();
    }

    public boolean validaCampos() {
        return !lista.stream().anyMatch(c -> c.getText().equals(""));
    }

    public boolean validaCombos() {
        return !listaCombo.stream().anyMatch(elemento -> elemento.getSelectedItem() == null);
    }

    public boolean validaChecks() {
        return !listaChecks.stream().anyMatch(elemento -> elemento.isSelected());
    }

    public boolean validaTablas() {
        listaTablas.forEach(System.out::println);
        return !listaTablas.stream().anyMatch(elemento -> elemento.getRowCount() == 0);
    }

    public void devuelveMensaje(int tipo) {
        switch (tipo) {
            case CAMPOS_VALIDADO:
                JOptionPane.showMessageDialog(componentePadre, "Todos los campos deben ser completados", "Error", JOptionPane.ERROR_MESSAGE);
                lista.stream().filter(c -> c.getText().equals("")).limit(1).forEach(c -> c.requestFocus());
                break;
            case COMBOS_VALIDADOS:
                JOptionPane.showMessageDialog(componentePadre, "Todos los combos deben ser completados", "Error", JOptionPane.ERROR_MESSAGE);
                listaCombo.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
                break;
            case CHECKS_VALIDADOS:
                JOptionPane.showMessageDialog(componentePadre, "Debe seleccionar por lo menos una opción del listado", "Error", JOptionPane.ERROR_MESSAGE);
                listaCombo.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
            case TABLAS_VALIDADAS:
                JOptionPane.showMessageDialog(componentePadre, "Todas las tablas deben tener por lo menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
                //listaCombo.stream().filter(elemento -> elemento.getSelectedItem() == null).forEach(elemento -> elemento.requestFocus());
                break;
        }

    }

    public void agregarCampoParaValidar(JTextComponent campo) {
        lista.add(campo);
    }

    public void agregarComboParaValidar(JComboBox combo) {
        listaCombo.add(combo);
    }

    public void agregarChecksParaValidar(JCheckBox check) {
        listaChecks.add(check);
    }
    
    public void agregarTablasParaValidar(TableModel tabla) {
        listaTablas.add(tabla);
    }
}
