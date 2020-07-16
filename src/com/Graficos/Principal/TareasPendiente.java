package com.Graficos.Principal;

import com.DAO.DAOException;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelos.Combos.ClientesComboModel;
import com.Modelos.Tablas.ModeloTablaTarea;
import com.modelo.Cliente;
import com.modelo.Tarea;

public class TareasPendiente extends javax.swing.JPanel {

    private ClientesComboModel modeloComboClientes;
    private ModeloTablaTarea modeloTablaTareas;
    private Tarea tarea;

    public TareasPendiente() {
        initComponents();
        modeloComboClientes = new ClientesComboModel();
        this.JCB_clientes.setModel(modeloComboClientes);
        this.tarea = new Tarea();
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

    public void setDAOClientes(DAOManager manager) {
        try {
            modeloComboClientes.setDAOClientes(manager.getDAOClientes());
            modeloComboClientes.actualizarModelo();
            modeloTablaTareas = new ModeloTablaTarea(manager.getDAOTareas(), manager.getDAOClientes());
            modeloTablaTareas.actualizarModelo();
            this.JTBL_tareas.setModel(modeloTablaTareas);
            modelarTabla();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JCB_clientes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTBL_tareas = new javax.swing.JTable();

        jLabel1.setText("TAREA:");

        jLabel2.setText("CLIENTE:");

        jLabel3.setText("FECHA CREACION:");

        jButton1.setText("CREAR");

        jButton2.setText("EDITAR");

        jButton3.setText("ELIMINAR");

        jButton4.setText("CONVERTIR");
        jButton4.setToolTipText("CONVIERTE EL ELEMENTO EN UNA TAREA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(JCB_clientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))))
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JCB_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Cliente> JCB_clientes;
    private javax.swing.JTable JTBL_tareas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
