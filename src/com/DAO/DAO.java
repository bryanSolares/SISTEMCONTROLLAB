package com.DAO;

import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

public interface DAO<T, V> {

    void crear(T parametro) throws DAOException;

    void editar(T parametro) throws DAOException;

    void borrar(V parametro) throws DAOException;

    Map<ResultSetMetaData, List<T>> buscarTodos() throws DAOException;

    T buscarUno(V parametro) throws DAOException;

}
