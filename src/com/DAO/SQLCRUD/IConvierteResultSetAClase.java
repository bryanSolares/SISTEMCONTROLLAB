
package com.DAO.SQLCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConvierteResultSetAClase <C>{

    C convierteElemento(ResultSet resultadoConsulta)throws SQLException;
    
}
