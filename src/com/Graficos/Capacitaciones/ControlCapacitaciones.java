package com.Graficos.Capacitaciones;

import com.DAO.DAOException;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.DAO.Recursos.Validaciones;
import com.Modelo.Capacitacion;
import com.Modelos.Tablas.ModeloTablaCapacitaciones;
import com.Modelos.Tablas.ModeloTablaCapacitacionesDetalle;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

public class ControlCapacitaciones extends javax.swing.JFrame {

    private DAOManager manager;
    private Capacitacion capacitacion;
    private ModeloTablaCapacitaciones modeloCapacitaciones;
    private ModeloTablaCapacitacionesDetalle modeloCapacitacionesDeta;
    private List<Capacitacion> listaCapacitaciones;
    private List<Capacitacion.CapacitacionDetalle> listaDetalleCapacitacion;

    public ControlCapacitaciones(DAOManager manager) {
        initComponents();
        this.manager = manager;
        inicializarComponentes();
        cargarDatosTablas();
        estadoBotonesIniciales(false);
    }

    private void inicializarComponentes() {
        this.datosCapacitacion.setManager(manager);
        this.datosCapacitacion.agregarModelosACombo();
        datosCapacitacion.setEditable(false);
        modeloCapacitaciones = new ModeloTablaCapacitaciones(manager.getDAOCapacitaciones());
        modeloCapacitacionesDeta = new ModeloTablaCapacitacionesDetalle(manager.getDAOCapacitaciones());
    }

    private void cargarDatosTablas() {
        try {

            modeloCapacitaciones.actualizarModelo();
            modeloCapacitaciones.fireTableDataChanged();
            listaCapacitaciones = modeloCapacitaciones.getListaDetalleCapacitaciones();
            dataCapacitaciones.setModel(modeloCapacitaciones);
            dataModulos.setModel(new DefaultTableModel());
            cargarModulosRelacionadosCapacitacionDetalle();

        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "No se puede cargar los modelos");
        }
    }

    private void cargarModulosRelacionadosCapacitacionDetalle() {
        dataCapacitaciones.getSelectionModel().addListSelectionListener(e -> {
            if (dataCapacitaciones.getSelectedRow() != -1) {
                try {
                    nuevo.setEnabled(false);
                    guardar.setEnabled(false);
                    cancelar.setEnabled(true);
                    editar.setEnabled(true);
                    borrar.setEnabled(true);
                    modeloCapacitacionesDeta.setCapacitacion(manager.getDAOCapacitaciones().buscarUno((Long) dataCapacitaciones.getValueAt(dataCapacitaciones.getSelectedRow(), 0)));
                    modeloCapacitacionesDeta.actualizarModelo();
                    listaDetalleCapacitacion = modeloCapacitacionesDeta.getListaDetalleCapacitacion();
                    modeloCapacitacionesDeta.fireTableDataChanged();
                    dataModulos.setModel(modeloCapacitacionesDeta);
                } catch (DAOException ex) {
                    GestionarRecursos.propagarError(ex, "No se puede cargar los modelos");
                }
            } else {
                dataModulos.setModel(new DefaultTableModel());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataCapacitaciones = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataModulos = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        nuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        editar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        salir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        datosCapacitacion = new com.Graficos.Capacitaciones.DatosCapacitacion();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Capacitaciones"));
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        dataCapacitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(dataCapacitaciones);

        jPanel2.add(jScrollPane1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Módulos de Sistema"));
        jPanel3.setLayout(new javax.swing.OverlayLayout(jPanel3));

        dataModulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(dataModulos);

        jPanel3.add(jScrollPane2);

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(0, null, java.awt.Color.white, null, null));
        jToolBar1.setRollover(true);

        nuevo.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/nuevo.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.setFocusable(false);
        nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevo);
        jToolBar1.add(jSeparator1);

        editar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/editar.png"))); // NOI18N
        editar.setText("Editar");
        editar.setEnabled(false);
        editar.setFocusable(false);
        editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jToolBar1.add(editar);

        borrar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/borrar.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setEnabled(false);
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jToolBar1.add(borrar);
        jToolBar1.add(jSeparator2);

        guardar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/guardar.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setEnabled(false);
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(guardar);

        cancelar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/cancelar.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setEnabled(false);
        cancelar.setFocusable(false);
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(cancelar);
        jToolBar1.add(jSeparator3);

        salir.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        salir.setText("Salir");
        salir.setFocusable(false);
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jToolBar1.add(salir);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datosCapacitacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datosCapacitacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        datosCapacitacion.setEditable(true);
        cancelar.setEnabled(true);
        borrar.setEnabled(false);
        editar.setEnabled(false);
        guardar.setEnabled(true);
        nuevo.setEnabled(false);
    }//GEN-LAST:event_nuevoActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        capacitacion = listaCapacitaciones.stream().filter(elemento -> Objects.equals(elemento.getIdCapacitacion(), dataCapacitaciones.getValueAt(dataCapacitaciones.getSelectedRow(), 0))).iterator().next();
        capacitacion.setDetalleModulos(listaDetalleCapacitacion);
        nuevo.setEnabled(false);
        guardar.setEnabled(true);
        datosCapacitacion.setCapacitacion(capacitacion);
        datosCapacitacion.setEditable(true);
        datosCapacitacion.cargarCapacitacion();
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
//        try {
//            eliminarCliente();
//            estadoBotonesIniciales(false);
//            datosCliente.setCliente(null);
//            datosCliente.cargarDatos();
//            datosCliente.setEditable(false);
//        } catch (DAOException ex) {
//            GestionarRecursos.propagarError("Probablemente el cliente que intenta eliminar tiene tareas existentes " + ex.getMessage());
//        }
    }//GEN-LAST:event_borrarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (capacitacion == null) {
            crearCapacitacion();
        } else {
            modificarCapacitacion();
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void crearCapacitacion() {
        try {
            if (datosCapacitacion.getValidaCombos().validaCombos()
                    && datosCapacitacion.grabarCapacitacion()) {
                datosCapacitacion.grabarCapacitacion();
                capacitacion = datosCapacitacion.getCapacitacion();
                manager.getDAOCapacitaciones().crear(capacitacion);
                capacitacion = null;
                datosCapacitacion.setCapacitacion(capacitacion);
                datosCapacitacion.cargarCapacitacion();
                datosCapacitacion.setEditable(false);
                estadoBotonesIniciales(false);
                cargarDatosTablas();
            } else {
                datosCapacitacion.getValidaCombos().devuelveMensaje(Validaciones.COMBOS_VALIDADOS);
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void modificarCapacitacion() {
        try {
            //verificar si no se ha eliminado algún modulo
            datosCapacitacion.grabarCapacitacion();
            datosCapacitacion.getListaModulos().forEach(e -> System.out.println(e.getEstadoSeleccion()));
            //System.out.println(datosCapacitacion.getListaModulos().getSelectedValuesList().size());

            //validar combos llenos
            //modificar
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        capacitacion = null;
        dataCapacitaciones.clearSelection();
        cargarDatosTablas();
        estadoBotonesIniciales(false);
        datosCapacitacion.setCapacitacion(capacitacion);
        datosCapacitacion.cargarCapacitacion();

        if (datosCapacitacion.isEditable()) {
            datosCapacitacion.setCapacitacion(null);
            datosCapacitacion.cargarCapacitacion();
            datosCapacitacion.setEditable(false);
        }
    }//GEN-LAST:event_cancelarActionPerformed

    private void estadoBotonesIniciales(boolean estado) {
        nuevo.setEnabled(true);
        guardar.setEnabled(estado);
        editar.setEnabled(estado);
        borrar.setEnabled(estado);
        cancelar.setEnabled(estado);
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTable dataCapacitaciones;
    private javax.swing.JTable dataModulos;
    private com.Graficos.Capacitaciones.DatosCapacitacion datosCapacitacion;
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
