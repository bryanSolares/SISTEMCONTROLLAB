package com.Graficos.Capacitaciones;

import com.DAO.DAOException;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelo.Capacitacion;
import com.DAO.Recursos.CheckPersonalizado;
import com.DAO.Recursos.Validaciones;
import com.Modelos.Combos.ClientesComboModel;
import com.Modelos.Combos.ParametrosComboModel;
import com.modelo.Cliente;
import com.modelo.Parametros;
import java.util.ArrayList;
import java.util.List;

public class DatosCapacitacion extends javax.swing.JPanel{

    private DAOManager manager;
    private ClientesComboModel modeloClientes;
    private ParametrosComboModel modeloTipoCapacitacion, modeloReponsable;
    private Capacitacion capacitacion;
    private Validaciones validaciones;
    private boolean componenetesEditables;
    private ConstruccionJList jListPersonalizado;

    public DatosCapacitacion() {
        initComponents();
        buscarSistemaAsociadoACliente();
        inicializarVariables();
        validarCombos();
    }

    private void inicializarVariables() {
        jListPersonalizado = new ConstruccionJList(contenedorModulos);
        jListPersonalizado.cargarJList((Cliente) clientes.getSelectedItem());
        validaciones = new Validaciones(this);
    }

    private void validarCombos() {
        validaciones.agregarComboParaValidar(clientes);
        validaciones.agregarComboParaValidar(responsable);
        validaciones.agregarComboParaValidar(tipoCapacitacion);
    }

    public final void agregarModelosACombo() {
        modeloClientes = new ClientesComboModel(manager.getDAOClientes());
        modeloTipoCapacitacion = new ParametrosComboModel();
        modeloReponsable = new ParametrosComboModel();
        clientes.setModel(modeloClientes);
        tipoCapacitacion.setModel(modeloTipoCapacitacion);
        responsable.setModel(modeloReponsable);
        cargaDatosAModelos();
    }

    private void cargaDatosAModelos() {
        try {
            modeloClientes.actualizarModelo();
            modeloTipoCapacitacion.actualizarCombo(ParametrosComboModel.TIPO_CAPACITACION);
            modeloReponsable.actualizarCombo(ParametrosComboModel.TIPO_REPONSABLE_SOPORTE);
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void buscarSistemaAsociadoACliente() {

        clientes.addItemListener((e) -> {
            sistema.setText((String) Parametros.devuelveValorParametro(clientes.getSelectedItem() != null ? ((Cliente) clientes.getSelectedItem()).getId_sistema() : 0, Parametros.DEVUELVE_VALOR));
            jListPersonalizado.cargarJList((Cliente) clientes.getSelectedItem());
        });
    }

    public boolean grabarCapacitacion() throws DAOException {

        if (!jListPersonalizado.getListaModulosSeleccionados().isEmpty()) {

            if (capacitacion == null) {
                capacitacion = new Capacitacion();
            }

            capacitacion.setIdCliente((Cliente) clientes.getSelectedItem());
            capacitacion.setIdSistema((Parametros) Parametros.devuelveValorParametro(((Cliente) clientes.getSelectedItem()).getId_sistema(), Parametros.DEVUELVE_PARAMETRO));
            capacitacion.setTipoCapacitacion(((Parametros) tipoCapacitacion.getSelectedItem()));
            capacitacion.setResponsable(((Parametros) responsable.getSelectedItem()));
            capacitacion.setEstadoActual(devuelveEstadoActualCapacitacion());
            capacitacion.setDetalleModulos(recuperarListaModulosSistema(capacitacion));
            return true;
        } else {
            validaciones.devuelveMensaje(Validaciones.CHECKS_VALIDADOS);
            return false;
        }

    }

    //pendiente finalizar método
    private int devuelveEstadoActualCapacitacion() {
        return Parametros.PENDIENTE.getId();
    }

    public List<Capacitacion.CapacitacionDetalle> recuperarListaModulosSistema(Capacitacion encabezado) {

        List<Capacitacion.CapacitacionDetalle> lista = new ArrayList<>();

        jListPersonalizado.getListaModulosSeleccionados().forEach(elemento -> {
            lista.add(encabezado.new CapacitacionDetalle(elemento.getDescripcionCheck(), elemento.getDescripcionCheck().toString(), Parametros.PENDIENTE));
        });

        jListPersonalizado.limpiarListaModulos();
        return lista;
    }

    public void cargarCapacitacion() {
        if (capacitacion != null) {
            clientes.setSelectedItem(capacitacion.getIdCliente());
            sistema.setText(capacitacion.getIdSistema().toString());
            tipoCapacitacion.setSelectedItem(capacitacion.getTipoCapacitacion());
            responsable.setSelectedItem(capacitacion.getResponsable());
            cargarListaModulosCapacitacion();
        } else {
            cargaDatosAModelos();
            sistema.setText("");
        }
    }

    private void cargarListaModulosCapacitacion() {
        jListPersonalizado.cargarJList(capacitacion.getDetalleModulos());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientes = new javax.swing.JComboBox<>();
        crear_cliente = new javax.swing.JButton();
        sistema = new javax.swing.JTextField();
        tipoCapacitacion = new javax.swing.JComboBox<>();
        responsable = new javax.swing.JComboBox<>();
        modulos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contenedorModulos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add(clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 19, 174, -1));

        crear_cliente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        crear_cliente.setText("+");
        crear_cliente.setPreferredSize(new java.awt.Dimension(45, 25));
        crear_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_clienteActionPerformed(evt);
            }
        });
        add(crear_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 18, -1, 23));

        sistema.setEnabled(false);
        add(sistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 67, 225, -1));

        add(tipoCapacitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 117, 225, -1));

        add(responsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 166, 225, -1));

        modulos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Módulos Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 12))); // NOI18N

        jScrollPane1.setViewportView(contenedorModulos);

        javax.swing.GroupLayout modulosLayout = new javax.swing.GroupLayout(modulos);
        modulos.setLayout(modulosLayout);
        modulosLayout.setHorizontalGroup(
            modulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        modulosLayout.setVerticalGroup(
            modulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modulosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(modulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 204, 243, 260));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel1.setText("Cliente");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel2.setText("Sistema Vendido");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 47, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel3.setText("Tipo Capacitación");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 97, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel4.setText("Responsable Capacitación");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 148, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void crear_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crear_clienteActionPerformed

    public void setManager(DAOManager manager) {
        this.manager = manager;
    }

    public Capacitacion getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }

    public boolean isEditable() {
        return componenetesEditables;
    }

    public void setEditable(boolean componenetesEditables) {
        this.componenetesEditables = componenetesEditables;
        clientes.setEnabled(componenetesEditables);
        tipoCapacitacion.setEnabled(componenetesEditables);
        responsable.setEnabled(componenetesEditables);
        crear_cliente.setEnabled(componenetesEditables);
        contenedorModulos.setEnabled(componenetesEditables);
        modulos.setEnabled(componenetesEditables);
    }

    public Validaciones getValidaCombos() {
        return validaciones;
    }

    public List<CheckPersonalizado> getListaModulos() {
        //return contenedorModulos.getModel().
        return jListPersonalizado.getListaModulosSeleccionados();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Cliente> clientes;
    private javax.swing.JList<CheckPersonalizado> contenedorModulos;
    private javax.swing.JButton crear_cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel modulos;
    private javax.swing.JComboBox<Parametros> responsable;
    private javax.swing.JTextField sistema;
    private javax.swing.JComboBox<Parametros> tipoCapacitacion;
    // End of variables declaration//GEN-END:variables

}
