
package com.DAO;

import com.Modelo.Capacitacion;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

public interface DAOCapacitaciones extends DAO<Capacitacion, Long>{

    Map<ResultSetMetaData, List<Capacitacion.CapacitacionDetalle>> buscarModulosRelacionados(Capacitacion capacitacion)throws DAOException;
    
}
