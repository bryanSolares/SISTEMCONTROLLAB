package com.DAO;

import com.Modelo.Tarea;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DAOTareas extends DAO<Tarea, Long> {

    void actualizarTiempos(List<Tarea> tarea) throws DAOException;

    Map<ResultSetMetaData, List<Tarea>> busquedaConFiltros(Long cliente, LocalDate fecha_ini, LocalDate fecha_fin) throws DAOException;
    
    Map<ResultSetMetaData, List<Tarea.TareaDetalle>> buscarDetalles (Long idTarea) throws DAOException;
    
    void actualizarTiempoTarea(Tarea tarea) throws DAOException;

}
