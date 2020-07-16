package com.Graficos.Principal;

import com.DAO.DAOException;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.DAO.Recursos.Validaciones;
import com.Modelos.Combos.ClientesComboModel;
import com.Modelos.Tablas.ModeloTablaTarea;
import com.modelo.Cliente;
import com.modelo.Parametros;
import com.modelo.Tarea;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class TareasPendiente extends javax.swing.JPanel {

    private ClientesComboModel modeloComboClientes;
    private ModeloTablaTarea modeloTablaTareas;
    private Tarea tarea;
    private DAOManager manager;
    private Validaciones validaciones;

    public TareasPendiente() {
        initComponents();
        modeloComboClientes = new ClientesComboModel();
        this.JCB_clientes.setModel(modeloComboClientes);
        this.tarea = new Tarea();

        JTBL_tareas.getSelectionModel().addListSelectionListener((e) -> {
            boolean seleccionValida = (JTBL_tareas.getSelectedRow() != -1);

            JB_crear.setEnabled(!seleccionValida);
            JB_editar.setEnabled(seleccionValida);
            JB_eliminar.setEnabled(seleccionValida);
            JB_convertir.setEnabled(seleccionValida);
            JB_cancelar.setEnabled(seleccionValida);

            if (seleccionValida) {
                tarea = modeloTablaTareas.getElementById((Long) JTBL_tareas.getValueAt(JTBL_tareas.getSelectedRow(), 0));
                cargarDatos();
            }

        });

        validaciones = new Validaciones(this);
        JD_fechainicio.setDate(new Date());
        agregarValidaciones();
    }

    private void modelarTabla() {

        this.JTBL_tareas.getTableHeader().setReorderingAllowed(false);
        this.JTBL_tareas.getTableHeader().setResizingAllowed(false);

        this.JTBL_tareas.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(1).setPreferredWidth(600);
        this.JTBL_tareas.getColumnModel().getColumn(2).setPreferredWidth(700);
        this.JTBL_tareas.getColumnModel().getColumn(3).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(4).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(5).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(6).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(7).setPreferredWidth(500);
        this.JTBL_tareas.getColumnModel().getColumn(8).setPreferredWidth(0);
        this.JTBL_tareas.getColumnModel().getColumn(9).setPreferredWidth(0);
    }

    private void actualizarModelos() throws DAOException {
        modeloComboClientes.actualizarModelo();
        modeloTablaTareas.actualizarModelo();
        this.JTBL_tareas.setModel(modeloTablaTareas);
    }

    private void guardarDatos() {
        if (tarea == null) {
            tarea = new Tarea();
        }

        tarea.setTitulo(JT_descripcion.getText());
        tarea.setCliente(JCB_clientes.getItemAt(JCB_clientes.getSelectedIndex()).getId());
        tarea.setDescripcion("");
        tarea.setPrioridad(Parametros.SIN_DEFINIR.getId());
        tarea.setEstado(Parametros.PORHACER.getId());
        tarea.setFechaInicio(JD_fechainicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        tarea.setFechaFin(JD_fechainicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        tarea.setAreaResponsable(Parametros.SIN_DEFINIR.getId());
        tarea.getListaDetalles().clear();
        tarea.setListaDetalles(new ArrayList<>());
    }

    private void cargarDatos() {
        if (tarea != null) {
            JCB_clientes.setSelectedItem(devuelveCliente());
            JT_descripcion.setText(tarea.getTitulo());
            JD_fechainicio.setDate(java.sql.Date.valueOf(tarea.getFechaInicio().toLocalDate()));
        } else {
            JT_descripcion.setText("");
            JD_fechainicio.setDate(new Date());
        }
    }

    private Cliente devuelveCliente() {
        try {
            return manager.getDAOClientes().buscarUno(tarea.getCliente());
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
            return null;
        }
    }

    public void setDAO(DAOManager manager) {
        try {
            this.manager = manager;
            modeloComboClientes.setDAOClientes(manager.getDAOClientes());
            modeloTablaTareas = new ModeloTablaTarea(manager.getDAOTareas(), manager.getDAOClientes());
            actualizarModelos();
            modelarTabla();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void agregarValidaciones() {
        this.validaciones.agregarCampoParaValidar(JT_descripcion);
        this.validaciones.agregarComboParaValidar(JCB_clientes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JT_descripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JD_fechainicio = new com.toedter.calendar.JDateChooser();
        JCB_clientes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        JB_crear = new javax.swing.JButton();
        JB_editar = new javax.swing.JButton();
        JB_eliminar = new javax.swing.JButton();
        JB_convertir = new javax.swing.JButton();
        JB_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTBL_tareas = new javax.swing.JTable();

        jLabel1.setText("TAREA:");

        jLabel2.setText("CLIENTE:");

        jLabel3.setText("FECHA CREACION:");

        JB_crear.setText("CREAR");
        JB_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_crearActionPerformed(evt);
            }
        });

        JB_editar.setText("EDITAR");
        JB_editar.setEnabled(false);
        JB_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_editarActionPerformed(evt);
            }
        });

        JB_eliminar.setText("ELIMINAR");
        JB_eliminar.setEnabled(false);
        JB_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_eliminarActionPerformed(evt);
            }
        });

        JB_convertir.setText("CONVERTIR");
        JB_convertir.setToolTipText("CONVIERTE EL ELEMENTO EN UNA TAREA");
        JB_convertir.setEnabled(false);

        JB_cancelar.setText("CANCELAR");
        JB_cancelar.setToolTipText("CONVIERTE EL ELEMENTO EN UNA TAREA");
        JB_cancelar.setEnabled(false);
        JB_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JB_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JB_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JB_convertir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JT_descripcion)
                            .addComponent(JCB_clientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JD_fechainicio, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))))
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JCB_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JD_fechainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_crear)
                    .addComponent(JB_editar)
                    .addComponent(JB_eliminar)
                    .addComponent(JB_convertir)
                    .addComponent(JB_cancelar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        JTBL_tareas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTBL_tareas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JB_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelarActionPerformed

        try {
            JT_descripcion.setEnabled(true);
            JCB_clientes.setEnabled(true);
            JD_fechainicio.setEnabled(true);
            JB_crear.setEnabled(true);

            JB_editar.setEnabled(false);
            JB_eliminar.setEnabled(false);
            JB_convertir.setEnabled(false);
            JB_cancelar.setEnabled(false);
            JTBL_tareas.clearSelection();

            tarea = null;
            cargarDatos();
            actualizarModelos();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_JB_cancelarActionPerformed

    private void JB_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_crearActionPerformed
        try {
            if (validaciones.validaCampos() && validaciones.validaCombos()) {
                guardarDatos();
                manager.getDAOTareas().crear(tarea);
                tarea = null;
                cargarDatos();
                actualizarModelos();
            } else {
                if (!validaciones.validaCampos()) {
                    validaciones.devuelveMensaje(Validaciones.CAMPOS_VALIDADO);
                } else {
                    validaciones.devuelveMensaje(Validaciones.COMBOS_VALIDADOS);
                }
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_JB_crearActionPerformed

    private void JB_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_editarActionPerformed
        try {
            if (validaciones.validaCampos() && validaciones.validaCombos()) {
                guardarDatos();
                manager.getDAOTareas().editar(tarea);
                tarea = null;
                cargarDatos();
                actualizarModelos();
            } else {
                if (!validaciones.validaCampos()) {
                    validaciones.devuelveMensaje(Validaciones.CAMPOS_VALIDADO);
                } else {
                    validaciones.devuelveMensaje(Validaciones.COMBOS_VALIDADOS);
                }
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_JB_editarActionPerformed

    private void JB_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_eliminarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(this, "Seguro que quiere eliminar este regitros", "Eliminar Cliente",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                manager.getDAOTareas().borrar(tarea.getId());
                tarea = null;
                cargarDatos();
                actualizarModelos();
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_JB_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancelar;
    private javax.swing.JButton JB_convertir;
    private javax.swing.JButton JB_crear;
    private javax.swing.JButton JB_editar;
    private javax.swing.JButton JB_eliminar;
    private javax.swing.JComboBox<Cliente> JCB_clientes;
    private com.toedter.calendar.JDateChooser JD_fechainicio;
    private javax.swing.JTable JTBL_tareas;
    private javax.swing.JTextField JT_descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
