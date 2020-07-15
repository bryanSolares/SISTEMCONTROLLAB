package com.Modelos.Tablas;

import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public abstract class ModeloTabla<T, D> extends AbstractTableModel {

    protected final D solicitaModelo;
    protected Map<ResultSetMetaData, List<T>> datosTabla = new HashMap<>();
    protected List<T> listaDatos;
    protected ResultSetMetaData metadatos;

    public ModeloTabla(D solicitaModelo) {
        this.solicitaModelo = solicitaModelo;
        this.listaDatos = new ArrayList<>();
    }

    protected void actualizarModelo()throws DAOException {
        this.listaDatos = datosTabla.values().iterator().next();
        this.metadatos = datosTabla.keySet().iterator().next();
    }

    @Override
    public String getColumnName(int column) {
        try {
            return datosTabla.keySet().stream().iterator().next().getColumnLabel(column + 1);
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return datosTabla.values().stream().iterator().next().size();
    }

    @Override
    public int getColumnCount() {
        int totalColumnas = 0;
        try {
            totalColumnas = datosTabla.keySet().stream().iterator().next().getColumnCount();
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
        return totalColumnas;
    }

    public List<T> getDataElements() {
        return this.datosTabla.values().iterator().next();
    }

    public ResultSetMetaData getDataHeader() {
        return this.datosTabla.keySet().iterator().next();
    }

    public void addElementToData(T element) {
        if (!listaDatos.contains(element)) {
            this.listaDatos.add(element);
            this.fireTableDataChanged();
        }
    }

    public void removeElementWithIndex(int index) {
        if(index <= listaDatos.size()){
            this.listaDatos.remove(index);
            this.fireTableDataChanged();
        }
    }
    
    public T getElementByIndex(int index){
        return this.listaDatos.get(index);
    }

}
