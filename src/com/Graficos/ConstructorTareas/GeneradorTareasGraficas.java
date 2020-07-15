package com.Graficos.ConstructorTareas;

import com.modelo.Parametros;
import java.util.List;
import java.util.Map;

public interface GeneradorTareasGraficas<k, v> {

    public List<k> listadoTareasComponent();

    void actualizarTiempos();

    public Map<Parametros, Integer> conteoTareas();

    public int contabilizarRegistros(int tipoEstado);

    void actualizarListadoClientes();

    public List<k> getListadoTareasGraficas();

    public List<v> getListaClientes();

}
