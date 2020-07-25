package com.test;

import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Graficos.Principal.MenuPrincipal;
import com.Modelo.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ProcesosAutomaticos extends TimerTask {

    private List<Tarea> lista;
    private MenuPrincipal elementoObservado;

    public ProcesosAutomaticos(MenuPrincipal elementoObservado) {
        this.elementoObservado = elementoObservado;
        lista = new ArrayList<>();
    }

    @Override
    public void run() {

        lista.clear();

        elementoObservado.getListadoTareas()
                .stream()
                .filter(t -> !t.getCronometro().getCronometraje().equals("00:00:00:00"))
                .forEach(t -> {
                    t.getTarea().setDuracionTarea(t.getCronometro().getCronometraje());
                    lista.add(t.getTarea());
                });

        if (!lista.isEmpty()) {
            try {
                elementoObservado.getManager().getDAOTareas().actualizarTiempos(lista);
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex, "Error al modificar tiempos en Procesos Automáticos");
            }
        }
    }

    public static int estableceTiempoMinutos(int minutos) {
        return minutos * 60000;
    }

}
