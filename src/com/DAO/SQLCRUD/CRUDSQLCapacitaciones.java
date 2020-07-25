package com.DAO.SQLCRUD;

import com.DAO.DAOCapacitaciones;
import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelo.Capacitacion;
import com.Modelo.Cliente;
import com.Modelo.Parametros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDSQLCapacitaciones implements DAOCapacitaciones, IConvierteResultSetAClase<Capacitacion> {

    private Connection conexion = null;
    private PreparedStatement consultaPreparada = null;
    private ResultSet resultadosConsulta = null;
    private DAOClientes daoClientes;

    private final String crearCapacitacion = "INSERT INTO CAPACITACIONES_ENC "
            + "(CLIENTE, SISTEMA, RESPONSABLE, ESTADO_ACTUAL,TIPO_CAPACITACION) "
            + " VALUES (?,?,?,?,?)";
    private final String crearCapacitacionDetalle = "INSERT INTO CAPACITACIONES_DET (ID_CAPACITACION, COD_MODULO, DESCRIPCION_MODULO, ESTADO)"
            + " VALUES (?,?,?,?)";
    private final String editarCapacitacion = "UPDATE CAPACITACIONES_ENC "
            + "SET  CLIENTE = ?, SISTEMA = ?, FECHA_FIN = ?, RESPONSABLE = ?, ESTADO_ACTUAL = ?, TIPO_CAPACITACION = ? "
            + "WHERE ID_CAPACITACION = ?";
    //private final String editarCapacitacionDetalle = "";
    private final String borrarCapacitacion = "DELETE FROM CAPACITACIONES_ENC WHERE ID_CAPACITACION = ?";
    //private final String borrarCapacitacionDetalle = "";
    private final String buscarTodasLasCapacitacion = "SELECT * FROM CAPACITACIONES_ENC";
    private final String buscarTodasLasCapacitacionesDetalle = "SELECT * FROM CAPACITACIONES_DET WHERE ID_CAPACITACION = ?";
    private final String buscarUnaCapacitacion = "SELECT * FROM CAPACITACIONES_ENC WHERE ID_CAPACITACION = ?";

    public CRUDSQLCapacitaciones(Connection conexion) {
        this.conexion = conexion;
        daoClientes = new CRUDSQLClientes(conexion);
    }

    @Override
    public void crear(Capacitacion parametro) throws DAOException {

        try {
            PreparedStatement insercionDetalle = conexion.prepareStatement(crearCapacitacionDetalle);
            conexion.setAutoCommit(false);

            consultaPreparada = conexion.prepareStatement(crearCapacitacion, Statement.RETURN_GENERATED_KEYS);
            consultaPreparada.setLong(1, parametro.getIdCliente().getId());
            consultaPreparada.setLong(2, parametro.getIdSistema().getId());
            consultaPreparada.setLong(3, parametro.getResponsable().getId());
            consultaPreparada.setInt(4, parametro.getEstadoActual());
            consultaPreparada.setLong(5, parametro.getTipoCapacitacion().getId());
            consultaPreparada.executeUpdate();

            Long idDisponible;
            ResultSet llaves = consultaPreparada.getGeneratedKeys();
            if (llaves.next()) {
                idDisponible = llaves.getLong(1);
            } else {
                throw new DAOException("No se puede asignar un ID");
            }

            parametro.getDetalleModulos().forEach(elemento -> {
                try {
                    insercionDetalle.setLong(1, idDisponible);
                    insercionDetalle.setLong(2, elemento.getCodModulo().getId());
                    insercionDetalle.setString(3, elemento.getDescripcionModulo());
                    insercionDetalle.setLong(4, elemento.getEstado().getId());
                    insercionDetalle.addBatch();
                } catch (SQLException ex) {
                    GestionarRecursos.propagarError(ex);
                }

            });

            insercionDetalle.executeBatch();
            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                GestionarRecursos.propagarError(ex1);
            }
            GestionarRecursos.propagarError(ex);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
    }

    @Override
    public void editar(Capacitacion parametro) throws DAOException {
        try {
            consultaPreparada = conexion.prepareStatement(editarCapacitacion);
            consultaPreparada.setLong(1, parametro.getIdCliente().getId());
            consultaPreparada.setLong(2, parametro.getIdSistema().getId());
            consultaPreparada.setString(3, parametro.getFecha_fin().toString());
            consultaPreparada.setLong(4, parametro.getResponsable().getId());
            consultaPreparada.setInt(5, parametro.getEstadoActual());
            consultaPreparada.setLong(6, parametro.getTipoCapacitacion().getId());
            consultaPreparada.setLong(7, parametro.getIdCapacitacion());

            consultaPreparada.executeUpdate();

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
    }

    @Override
    public void borrar(Long parametro) throws DAOException {
        try {
            consultaPreparada = conexion.prepareStatement(borrarCapacitacion);
            consultaPreparada.setLong(1, parametro);

            consultaPreparada.executeLargeUpdate();

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
    }

    @Override
    public Capacitacion convierteElemento(ResultSet resultadosConsulta) throws SQLException{
        Capacitacion capacitacion;
        Long id = resultadosConsulta.getLong(1);
        Cliente cliente = null;
        try {
            cliente = daoClientes.buscarUno(resultadosConsulta.getLong(2));
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
        Parametros idSistema = (Parametros) Parametros.devuelveValorParametro(resultadosConsulta.getInt(3), Parametros.DEVUELVE_PARAMETRO);
        LocalDate fechaFin = resultadosConsulta.getDate(4)!=null?resultadosConsulta.getDate(4).toLocalDate():LocalDate.MIN;
        Parametros idResponsable = (Parametros) Parametros.devuelveValorParametro(resultadosConsulta.getInt(5), Parametros.DEVUELVE_PARAMETRO);
        Integer estadoActual = resultadosConsulta.getInt(6);
        Parametros tipoCapacitacion = (Parametros) Parametros.devuelveValorParametro(resultadosConsulta.getInt(7), Parametros.DEVUELVE_PARAMETRO);

        capacitacion = new Capacitacion(cliente, idSistema, idResponsable, estadoActual, tipoCapacitacion);
        capacitacion.setFecha_fin(fechaFin);
        capacitacion.setIdCapacitacion(id);
        return capacitacion;

    }

    @Override
    public Map<ResultSetMetaData, List<Capacitacion>> buscarTodos() throws DAOException {

        Map<ResultSetMetaData, List<Capacitacion>> resultadoBusqueda = new HashMap<>();
        List<Capacitacion> datos = new ArrayList<>();
        ResultSetMetaData metadatosTabla;

        try {

            consultaPreparada = conexion.prepareStatement(buscarTodasLasCapacitacion);
            resultadosConsulta = consultaPreparada.executeQuery();
            metadatosTabla = resultadosConsulta.getMetaData();

            while (resultadosConsulta.next()) {
                datos.add(convierteElemento(resultadosConsulta));
            }

            resultadoBusqueda.put(metadatosTabla, datos);
            return resultadoBusqueda;

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
        return null;
    }

    @Override
    public Capacitacion buscarUno(Long parametro) throws DAOException {

        Capacitacion capacitacion = null;

        try {
            consultaPreparada = conexion.prepareStatement(buscarUnaCapacitacion);
            consultaPreparada.setLong(1, parametro);
            resultadosConsulta = consultaPreparada.executeQuery();

            while (resultadosConsulta.next()) {
                capacitacion = convierteElemento(resultadosConsulta);
            }

            return capacitacion;

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(consultaPreparada);
            GestionarRecursos.cerrarResultSet(resultadosConsulta);
        }
        return null;
    }

    @Override
    public Map<ResultSetMetaData, List<Capacitacion.CapacitacionDetalle>> buscarModulosRelacionados(Capacitacion capacitacion) throws DAOException {

        ResultSet resultset = null;
        PreparedStatement preparedSta = null;
        Map<ResultSetMetaData, List<Capacitacion.CapacitacionDetalle>> resultadoBusqueda = new HashMap<>();
        List<Capacitacion.CapacitacionDetalle> datos = new ArrayList<>();
        ResultSetMetaData metadatosTabla;

        try {

            preparedSta = conexion.prepareStatement(buscarTodasLasCapacitacionesDetalle);
            preparedSta.setLong(1, capacitacion.getIdCapacitacion());
            resultset = preparedSta.executeQuery();
            metadatosTabla = resultset.getMetaData();

            while (resultset.next()) {
                datos.add(convierteDetalleCapacitacion(resultset, capacitacion));
            }

            resultadoBusqueda.put(metadatosTabla, datos);
            return resultadoBusqueda;

        } catch (SQLException e) {
            GestionarRecursos.propagarError(e);
        } finally {
            //GestionarRecursos.cerrarConexion(conexion);
            GestionarRecursos.cerrarPreparedStatement(preparedSta);
            GestionarRecursos.cerrarResultSet(resultset);
        }
        return null;
    }

    private Capacitacion.CapacitacionDetalle convierteDetalleCapacitacion(ResultSet resultado, Capacitacion padre) throws SQLException {
        Capacitacion.CapacitacionDetalle detalle;
        Parametros modulo = (Parametros) Parametros.devuelveValorParametro(resultado.getInt(2), Parametros.DEVUELVE_PARAMETRO);
        String descripcion = resultado.getString(3);
        LocalDate fecha_ini = resultado.getDate(4) != null ? resultado.getDate(4).toLocalDate() : LocalDate.MIN;
        LocalDate fecha_fin = resultado.getDate(5) != null ? resultado.getDate(5).toLocalDate() : LocalDate.MIN;
        Parametros estado = (Parametros) Parametros.devuelveValorParametro(resultado.getInt(6), Parametros.DEVUELVE_PARAMETRO);

        detalle = new Capacitacion().new CapacitacionDetalle(modulo, descripcion, estado);
        detalle.setIdCapacitacion(padre);
        return detalle;
    }

}
