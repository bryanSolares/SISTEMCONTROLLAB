package com.DAO.SQLCRUD;

import com.DAO.DAOException;
import com.DAO.DAOTareas;
import com.DAO.Recursos.GestionarRecursos;
import com.modelo.Tarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDSQLTareas implements DAOTareas, IConvierteResultSetAClase<Tarea> {

    private Connection conexion = null;
    private ResultSet resultados = null;
    private PreparedStatement consultaPreparada = null;

    private final String guardarTarea
            = "INSERT INTO TAREAS(TITULO,ID_CLIENTE,DESCRIPCION,PRIORIDAD,ESTADO,DURACION,FECHA_INICIO,FECHA_FIN,AREA)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";
    private final String editarTarea
            = "UPDATE TAREAS SET TITULO = ?,ID_CLIENTE = ?,DESCRIPCION = ?,PRIORIDAD = ?,"
            + "ESTADO = ?,DURACION = ?, FECHA_INICIO = ?,FECHA_FIN = ?, AREA = ? "
            + "WHERE ID_TAREA = ?";
    private final String borrarTarea = "DELETE FROM TAREAS WHERE ID_TAREA = ?";
    private final String buscarTodasLasTareas = "SELECT * FROM TAREAS WHERE ESTADO NOT IN (33,34) ";
    private final String buscarUnaTarea = "SELECT * FROM TAREAS WHERE ID_TAREA = ?";
    private final String modificaTiempos = "UPDATE TAREAS SET DURACION = ? WHERE ID_TAREA = ?";

    private final String agregarDetalleTarea = "INSERT INTO TAREAS_DETALLE (ID_TAREA,DESCRIPCION) VALUES (?,?)";
    private final String editarDetalleTarea = "UPDATE TAREAS_DETALLE SET DESCRIPCION = ? WHERE ID_DETALLE = ?";
    private final String borrarDetalleTarea = "DELETE FROM TAREAS_DETALLE WHERE ID_TAREA = ?";
    private final String buscarTodosLosDetalles = "SELECT * FROM TAREAS_DETALLE WHERE ID_TAREA = ?";
    
//    ALTER TABLE `TAREAS_DETALLE` ADD CONSTRAINT `FK_ID_TAREA` FOREIGN KEY (`ID_TAREA`) REFERENCES `TAREAS`(`ID_TAREA`) ON DELETE RESTRICT ON UPDATE RESTRICT;

    public CRUDSQLTareas(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(Tarea tarea) throws DAOException {
        try {

            conexion.setAutoCommit(false);

            consultaPreparada = conexion.prepareStatement(guardarTarea, Statement.RETURN_GENERATED_KEYS);
            consultaPreparada.setString(1, tarea.getTitulo());
            consultaPreparada.setLong(2, tarea.getCliente());
            consultaPreparada.setString(3, tarea.getDescripcion());
            consultaPreparada.setInt(4, tarea.getPrioridad());
            consultaPreparada.setInt(5, tarea.getEstado());
            consultaPreparada.setString(6, tarea.getDuracionTarea());
            consultaPreparada.setTimestamp(7, Timestamp.valueOf(tarea.getFechaInicio()));
            consultaPreparada.setTimestamp(8, Timestamp.valueOf(tarea.getFechaFin()));
            consultaPreparada.setInt(9, tarea.getAreaResponsable());
            consultaPreparada.executeUpdate();

            Long idDisponible;
            ResultSet llave = consultaPreparada.getGeneratedKeys();

            if (llave.next()) {
                idDisponible = llave.getLong(1);
            } else {
                throw new DAOException("No se puede asignar un ID");
            }

            crearDetalles(tarea, idDisponible);

            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                GestionarRecursos.propagarError(ex1);
            }
            throw new DAOException("Error al insertar Tarea, no se ha finalizado la consulta", ex);
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    private void crearDetalles(Tarea tarea, Long idDisponible) {
        try {
            PreparedStatement insercionDetalle = conexion.prepareStatement(agregarDetalleTarea);
            tarea.getListaDetalles().forEach(e -> {
                try {
                    insercionDetalle.setLong(1, idDisponible);
                    insercionDetalle.setString(2, e.getDescripcion());
                    insercionDetalle.addBatch();
                } catch (SQLException ee) {
                    GestionarRecursos.propagarError(ee);
                }
            });

            insercionDetalle.executeBatch();
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    @Override
    public void editar(Tarea tarea) throws DAOException {
        try {

            conexion.setAutoCommit(false);

            consultaPreparada = conexion.prepareStatement(editarTarea);
            consultaPreparada.setString(1, tarea.getTitulo());
            consultaPreparada.setLong(2, tarea.getCliente());
            consultaPreparada.setString(3, tarea.getDescripcion());
            consultaPreparada.setInt(4, tarea.getPrioridad());
            consultaPreparada.setInt(5, tarea.getEstado());
            consultaPreparada.setString(6, tarea.getDuracionTarea());
            consultaPreparada.setTimestamp(7, Timestamp.valueOf(tarea.getFechaInicio()));
            consultaPreparada.setTimestamp(8, Timestamp.valueOf(tarea.getFechaFin()));
            consultaPreparada.setInt(9, tarea.getAreaResponsable());
            consultaPreparada.setLong(10, tarea.getId());

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al modificar Tarea");
            }

            borrarDetalles(tarea);
            crearDetalles(tarea, tarea.getId());

            conexion.commit();

        } catch (DAOException | SQLException e) {
            try {
                conexion.rollback();
                System.out.println(e);
                throw new DAOException("Error al Modificar Tarea, no se finalizo la consulta", e);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(e);
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    @Override
    public void borrar(Long id) throws DAOException {
        try {
            consultaPreparada = conexion.prepareStatement(borrarTarea);
            consultaPreparada.setLong(1, id);

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar Tarea");
            }

        } catch (DAOException | SQLException e) {
            throw new DAOException("Error al eliminar Tarea, no se finalizo la consulta", e);
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
        }
    }

    private void borrarDetalles(Tarea tarea) throws DAOException {
        try {
            consultaPreparada = conexion.prepareStatement(borrarDetalleTarea);
            consultaPreparada.setLong(1, tarea.getId());

            if (consultaPreparada.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar detalles");
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException("Error al eliminar Tarea, no se finalizo la consulta", e);
        }
    }

    @Override
    public Tarea convierteElemento(ResultSet resultado) throws SQLException {
        Tarea tarea;

        Long id = resultado.getLong(1);
        String titulo = resultado.getString(2);
        Long cliente = resultado.getLong(3);
        String descripcion = resultado.getString(4);
        int prioridad = resultado.getInt(5);
        int estado = resultado.getInt(6);
        String duracionTarea = resultado.getString(7);
        LocalDateTime horaInicio = LocalDateTime.of(resultado.getDate(8).toLocalDate(), resultado.getTime(8).toLocalTime());
        LocalDateTime horaFin = LocalDateTime.of(resultado.getDate(9).toLocalDate(), resultado.getTime(9).toLocalTime());
        int areaResponsable = resultado.getInt(10);

        tarea = new Tarea(titulo, cliente, prioridad, estado);
        tarea.setId(id);
        tarea.setDescripcion(descripcion);
        tarea.setDuracionTarea(duracionTarea);
        tarea.setFechaInicio(horaInicio);
        tarea.setFechaFin(horaFin);
        tarea.setAreaResponsable(areaResponsable);
        
        try {
            tarea.setListaDetalles(buscarDetallesLista(id)); ;
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }

        return tarea;
    }

    @Override
    public Map<ResultSetMetaData, List<Tarea>> buscarTodos() throws DAOException {

        try {
            consultaPreparada = conexion.prepareStatement(buscarTodasLasTareas);
            resultados = consultaPreparada.executeQuery();

            return devuelveListadoTareasConMetadatos(resultados);

        } catch (SQLException e) {
            throw new DAOException("Error en la llamada de todos los Datos");
        } finally {
//            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
//            GestionarRecursos.cerrarResultSet(resultados);
        }

    }

    @Override
    public Tarea buscarUno(Long id) throws DAOException {
        Tarea tarea = null;
        try {
            consultaPreparada = conexion.prepareStatement(buscarUnaTarea);
            consultaPreparada.setLong(1, id);
            resultados = consultaPreparada.executeQuery();

            if (resultados.next()) {
                tarea = convierteElemento(resultados);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al buscar Tarea Consulta no Finalizada");
        } finally {
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultados);
        }

        return tarea;
    }

    @Override
    public void actualizarTiempos(List<Tarea> tareas) throws DAOException {
        try {

            conexion.setAutoCommit(false);
            if (!tareas.isEmpty()) {
                consultaPreparada = conexion.prepareStatement(modificaTiempos);
                tareas.forEach(t -> {
                    try {
                        consultaPreparada.setString(1, t.getDuracionTarea());
                        consultaPreparada.setLong(2, t.getId());
                        consultaPreparada.addBatch();
                    } catch (SQLException ex) {
                        GestionarRecursos.propagarError(ex);
                    }
                });

                consultaPreparada.executeBatch();
                conexion.commit();
            }

        } catch (SQLException e) {
            try {
                conexion.rollback();
                GestionarRecursos.propagarError(e);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    @Override
    public void actualizarTiempoTarea(Tarea tarea) throws DAOException {
        try {
            conexion.setAutoCommit(false);
            consultaPreparada = conexion.prepareStatement(modificaTiempos);
            consultaPreparada.setString(1, tarea.getDuracionTarea());
            consultaPreparada.setLong(2, tarea.getId());
            consultaPreparada.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            try {
                conexion.rollback();
                GestionarRecursos.propagarError(e);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
                GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            } catch (SQLException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }

    @Override
    public Map<ResultSetMetaData, List<Tarea>> busquedaConFiltros(Long cliente, LocalDate fecha_ini, LocalDate fecha_fin) throws DAOException {

        String filtro1 = "SELECT * FROM TAREAS WHERE ID_CLIENTE = ?";
        String filtro2 = "SELECT * FROM TAREAS WHERE FECHA_INICIO >= ? AND FECHA_FIN <= ?";
        String filtro3 = "SELECT * FROM TAREAS WHERE ID_CLIENTE = ? AND FECHA_INICIO >= ? AND FECHA_FIN <= ?";

        try {

            if (cliente == null && fecha_ini == null && fecha_fin == null) {

                return buscarTodos();

            } else if (cliente != null && fecha_ini == null && fecha_fin == null) {

                consultaPreparada = conexion.prepareStatement(filtro1);
                consultaPreparada.setLong(1, cliente);
                resultados = consultaPreparada.executeQuery();

                return devuelveListadoTareasConMetadatos(resultados);

            } else if (cliente == null && fecha_ini != null && fecha_fin != null) {

                consultaPreparada = conexion.prepareStatement(filtro2);
                consultaPreparada.setTimestamp(1, Timestamp.valueOf(fecha_ini.atTime(LocalTime.MIN)));
                consultaPreparada.setTimestamp(2, Timestamp.valueOf(fecha_fin.atTime(LocalTime.MAX)));
                resultados = consultaPreparada.executeQuery();

                return devuelveListadoTareasConMetadatos(resultados);

            } else if (cliente != null && fecha_ini != null && fecha_fin != null) {

                consultaPreparada = conexion.prepareStatement(filtro3);
                consultaPreparada.setLong(1, cliente);
                consultaPreparada.setTimestamp(2, Timestamp.valueOf(fecha_ini.atTime(LocalTime.MIN)));
                consultaPreparada.setTimestamp(3, Timestamp.valueOf(fecha_fin.atTime(LocalTime.MAX)));
                resultados = consultaPreparada.executeQuery();

                return devuelveListadoTareasConMetadatos(resultados);

            }

        } catch (DAOException | SQLException e) {
            throw new DAOException("Error en consulta avanzada" + getClass() + e);
        } finally {
//            GestionarRecursos.cerrarPreparedStatement(consulta);
//            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
        return null;

    }

    private Map<ResultSetMetaData, List<Tarea>> devuelveListadoTareasConMetadatos(ResultSet resultadoTareas) throws DAOException {

        try {
            Map<ResultSetMetaData, List<Tarea>> mapeoDatos = new HashMap<>();
            ResultSetMetaData metadatos;
            List<Tarea> listaElementos = new ArrayList<>();

            consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);

            while (resultadoTareas.next()) {
                consultaPreparada.setLong(1, resultadoTareas.getLong(1));
                listaElementos.add(convierteElemento(resultadoTareas));
            }

            metadatos = resultadoTareas.getMetaData();
            mapeoDatos.put(metadatos, listaElementos);

            return mapeoDatos;
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
            return null;
        }

    }

    @Override
    public Map<ResultSetMetaData, List<Tarea.TareaDetalle>> buscarDetalles(Long idTarea) throws DAOException {
        Map<ResultSetMetaData, List<Tarea.TareaDetalle>> datosTabla = new HashMap<>();
        Tarea t = new Tarea();
        ResultSetMetaData metadata;

        try {
            consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);
            consultaPreparada.setLong(1, idTarea);
            resultados = consultaPreparada.executeQuery();

            while (resultados.next()) {
                t.agregarADetalle(resultados.getInt(1), resultados.getInt(2), resultados.getString(3));
            }

            metadata = resultados.getMetaData();
            datosTabla.put(metadata, t.getListaDetalles());

            return datosTabla;

        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
            return null;
        }
    }

    public List<Tarea.TareaDetalle> buscarDetallesLista(Long idTarea) throws DAOException {

        try {
            List<Tarea.TareaDetalle> detalles = new ArrayList<>();
            //consultaPreparada = conexion.prepareStatement(buscarTodosLosDetalles);
            consultaPreparada.setLong(1, idTarea);
            ResultSet result = consultaPreparada.executeQuery();

            while (result.next()) {
                detalles.add(new Tarea().new TareaDetalle(result.getInt(1), result.getInt(2), result.getString(3)));
            }

            return detalles;
        } catch (SQLException ex) {
            GestionarRecursos.propagarError(ex);
            return null;
        }
    }
}
