package com.Graficos.ConstructorTareas;

import com.DAO.DAOException;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.DAO.Recursos.RetoquesSwing;
import com.Graficos.Principal.MenuPrincipal;
import com.modelo.Cliente;
import com.modelo.Parametros;
import com.modelo.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModeloUnoTareas implements GeneradorTareasGraficas<TareaIndividual, Cliente> {

    private DAOManager manager;
    private List<Tarea> listadoTareasDatos;
    private List<TareaIndividual> listadoTareasGraficas;
    private List<Cliente> listaClientes;
    private MenuPrincipal padre;

    public ModeloUnoTareas(DAOManager manager, MenuPrincipal padre) {
        this.manager = manager;
        this.padre = padre;
        this.listadoTareasDatos = new ArrayList<>();
        this.listadoTareasGraficas = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public List<TareaIndividual> listadoTareasComponent() {
        try {

            if (!listadoTareasDatos.isEmpty()) {
                actualizarTiempos();
            }

            listadoTareasDatos.clear();
            listadoTareasGraficas.clear();

            listadoTareasDatos = manager.getDAOTareas().buscarTodos().values().iterator().next();

            listadoTareasDatos.forEach(e -> {
                try {
                    String nombreCliente = listaClientes.parallelStream()
                            .filter(cliente -> cliente.getId().equals(e.getCliente()))
                            .map(Cliente::getNombre)
                            .iterator().next();
                    RetoquesSwing color = new RetoquesSwing();
                    TareaIndividual tareaIndividual = new TareaIndividual(e, manager, color.generaColor(), this.padre, nombreCliente, color.getNombreColor());
                    iniciarCronometraje(tareaIndividual);
                    listadoTareasGraficas.add(tareaIndividual);
                } catch (DAOException ex) {
                    GestionarRecursos.propagarError(ex);
                }
            });

        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
            return listadoTareasGraficas;
        }
        return listadoTareasGraficas;
    }

    private void iniciarCronometraje(TareaIndividual tareaIndividual) {
        if (tareaIndividual.getTarea().getEstado() == Parametros.PROCESO.getId()) {
            tareaIndividual.getCronometro().iniciar();
        } else if (tareaIndividual.getTarea().getEstado() == Parametros.PENDIENTE.getId()) {
            tareaIndividual.getCronometro().pausar();
        } else if (tareaIndividual.getTarea().getEstado() == Parametros.INICIAL.getId()) {
            tareaIndividual.getCronometro().reiniciar();
        }
    }

    @Override
    public Map<Parametros, Integer> conteoTareas() {
        return null;
    }

    @Override
    public int contabilizarRegistros(int tipoEstado) {
        return (int) listadoTareasGraficas.stream().map(t -> t.getTarea()).filter(t -> t.getEstado() == tipoEstado).count();
    }

    @Override
    public void actualizarListadoClientes() {
        try {
            listaClientes = manager.getDAOClientes().buscarTodos().values().stream().iterator().next();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    @Override
    public void actualizarTiempos() {
        if (!listadoTareasDatos.isEmpty()) {
            try {
                manager.getDAOTareas().actualizarTiempos(listadoTareasGraficas.parallelStream()
                        .filter(tarea -> tarea.getTarea().getEstado() == Parametros.PROCESO.getId())
                        .map(TareaIndividual::getTarea)
                        .collect(Collectors.toList()));
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    @Override
    public List<TareaIndividual> getListadoTareasGraficas() {
        return listadoTareasGraficas;
    }

    @Override
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

}
