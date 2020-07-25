package com.Modelos.Combos;

import com.Modelo.Parametros;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ModeloComboParametros extends DefaultComboBoxModel<Parametros> {

    public static final int TIPO_CLIENTE = 1;
    public static final int TIPO_PRIORIDAD = 2;
    public static final int TIPO_ESTADO_TAREA = 3;
    public static final int TIPO_ESTADO_CLIENTE = 4;
    public static final int TIPO_REPONSABLE_SOPORTE = 5;
    public static final int TIPO_SISTEMA = 6;
    public static final int TIPO_CAPACITACION = 7;
    
    private Parametros parametros;

    public ModeloComboParametros() {
        parametros = Parametros.ACTIVO;
    }

    public void actualizarCombo(int tipoActualizacion) {
        switch (tipoActualizacion) {
            case TIPO_CLIENTE:
                actualizar(parametros.devuelveParametrosTipoCliente());
                break;
            case TIPO_PRIORIDAD:
                actualizar(parametros.devuelveParametrosTipoPrioridad());
                break;
            case TIPO_ESTADO_TAREA:
                actualizar(parametros.devuelveParametrosTipoEstadoTarea());
                break;
            case TIPO_ESTADO_CLIENTE:
                actualizar(parametros.devuelveParametrosTipoEstadoCliente());
                break;
            case TIPO_REPONSABLE_SOPORTE:
                actualizar(parametros.devuelveParametrosTipoEncargadoSoporte());
                break;
            case TIPO_SISTEMA:
                actualizar(parametros.devuelveParametrosTipoSistema());
                break;
            case TIPO_CAPACITACION:
                actualizar(parametros.devuelveParametrosTipoCapacitacion());
                break;
        }
    }

    private void actualizar(List<Parametros> lista) {
        if (lista != null && !lista.isEmpty()) {
            removeAllElements();
            lista.forEach(elemento -> {
                addElement(elemento);
            });
        }
    }


}
