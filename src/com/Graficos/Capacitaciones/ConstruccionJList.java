package com.Graficos.Capacitaciones;

import com.DAO.Recursos.CheckPersonalizado;
import com.Modelo.Capacitacion;
import com.Modelos.Tablas.ModeloJList;
import com.modelo.Cliente;
import com.modelo.Parametros;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JList;

public class ConstruccionJList {

    private JList<CheckPersonalizado> contenedor;
    private CheckPersonalizado modeloCheck;
    private CheckPersonalizado.AccionCheck comportamientoCheck;
    private List<CheckPersonalizado> listaCheckParaJList;
    private ModeloJList modeloJList;

    private List<Parametros> listaModulosDisponibles;

    public ConstruccionJList(JList contenedor) {
        this.contenedor = contenedor;
        listaCheckParaJList = new ArrayList<>();
        listaModulosDisponibles = new ArrayList<>();
        modeloJList = new ModeloJList(listaCheckParaJList);
        modeloCheck = new CheckPersonalizado();
        comportamientoCheck = new CheckPersonalizado().new AccionCheck(contenedor);
        contenedor.setModel(modeloJList);
        contenedor.setCellRenderer(modeloCheck);
        contenedor.addMouseListener(comportamientoCheck);
    }

    public void cargarJList(Cliente cliente) {
        if (cliente != null) {
            listaModulosDisponibles.clear();
            listaCheckParaJList.clear();
            listaModulosDisponibles = Parametros.SIN_DEFINIR.devuelveListaModulos((Parametros) Parametros.devuelveValorParametro(cliente.getId_sistema(), Parametros.DEVUELVE_PARAMETRO));
            cargarListaChecksParaJList(listaModulosDisponibles, false);
            modeloJList.cargarLista();
        }
       
    }

    public void cargarJList(List<Capacitacion.CapacitacionDetalle> lista) {
        if (!lista.isEmpty()) {
            cargarListaChecksParaJList(lista
                    .stream()
                    .map(Capacitacion.CapacitacionDetalle::getCodModulo)
                    .collect(Collectors.toList()), true);
            modeloJList.cargarLista();
            comportamientoCheck.setLista(listaCheckParaJList);
        }
    }

    private void cargarListaChecksParaJList(List<Parametros> lista, boolean estadoActualCheck) {

        lista.forEach(e -> {
            modeloCheck = new CheckPersonalizado(e);
            modeloCheck.estableceEstado(estadoActualCheck);
            listaCheckParaJList.add(modeloCheck);
        });
        modeloJList.setListaModulos(listaCheckParaJList);
    }
    
    public List<CheckPersonalizado> getListaModulosSeleccionados(){
        return comportamientoCheck.getLista();
    }
    
    public void limpiarListaModulos(){
        listaModulosDisponibles.clear();
        listaCheckParaJList.clear();
        comportamientoCheck.limpiarLista();
        modeloJList.removeAllElements();
    }
}
