package com.Graficos.Principal;

import com.DAO.Recursos.Animaciones;
import com.Graficos.Cliente.Clientes;
import com.Graficos.ConstructorTareas.TareaIndividual;
import com.Graficos.Tarea.DatosTarea;
import com.DAO.DAOManager;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Graficos.ConstructorTareas.GeneradorTareasGraficas;
import com.Graficos.ConstructorTareas.ModeloUnoTareas;
import com.test.ProcesosAutomaticos;
import com.modelo.*;
import java.awt.GridLayout;
import java.util.List;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MenuPrincipal extends javax.swing.JFrame {

    private final DAOManager manager;
    private final GeneradorTareasGraficas modeloUno;
    private final int FILTROCLIENTE = 1, FILTROPROCESO = 2, FILTROINICIAL = 3, FILTROPENDIENTE = 4, FILTRONINGUNO = 5;

    public MenuPrincipal(DAOManager manager) {
        initComponents();
        this.JP_pendientes.setDAO(manager);
        this.manager = manager;
        modeloUno = new ModeloUnoTareas(manager, this);
        GridLayout grid = new GridLayout(5, 2, 2, 2);
        panel_tareas.setLayout(grid);
        iniciar();
    }

    private void iniciar() {
        modeloUno.actualizarListadoClientes();
        visualizarTareasPendientes();
        iniciarAnimaciones();
        procesoAutomatico();
        try {
            setIconImage(new ImageIcon(getClass().getResource(manager.rutaImagenProyecto())).getImage());
        } catch (Exception ex) {
            GestionarRecursos.propagarError(ex, "No se ha encontrado la imagen del proyecto favor verificar");
        }
    }

    public void visualizarTareasPendientes() {

        panel_tareas.removeAll();
        modeloUno.listadoTareasComponent().forEach(e -> {
            panel_tareas.add((TareaIndividual) e);
        });
        panel_tareas.updateUI();
        contabilizarRegistros();
    }

    private void iniciarAnimaciones() {
        clientes.addMouseListener(new Animaciones(clientes));
        tareas.addMouseListener(new Animaciones(tareas));
        listado.addMouseListener(new Animaciones(listado));
        reportes.addMouseListener(new Animaciones(reportes));
        salir.addMouseListener(new Animaciones(salir));
        cuentaproceso.addMouseListener(new Animaciones(cuentaproceso));
        cuentainicio.addMouseListener(new Animaciones(cuentainicio));
        cuentapendientes.addMouseListener(new Animaciones(cuentapendientes));
        cuentatotal.addMouseListener(new Animaciones(cuentatotal));
    }

    private void procesoAutomatico() {
        ProcesosAutomaticos pa = new ProcesosAutomaticos(this);
        new Timer().schedule(pa, 0, ProcesosAutomaticos.estableceTiempoMinutos(20));
    }

    private void contabilizarRegistros() {

        int proceso = modeloUno.contabilizarRegistros(Parametros.PROCESO.getId());
        int inicio = modeloUno.contabilizarRegistros(Parametros.INICIAL.getId());
        int pendientes = modeloUno.contabilizarRegistros(Parametros.PENDIENTE.getId());

        int total = proceso + inicio + pendientes;

        cuentaproceso.setText(proceso + " Tarea en estado Activo.");
        cuentainicio.setText(inicio + " Tareas en estado Inicial.");
        cuentapendientes.setText(pendientes + " Tareas en estado Pendiente.");
        cuentatotal.setText(total + " Tareas en Total.");
    }

    public List<TareaIndividual> getListadoTareas() {
        return modeloUno.getListadoTareasGraficas();
    }

    public DAOManager getManager() {
        return manager;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clientes = new javax.swing.JLabel();
        tareas = new javax.swing.JLabel();
        listado = new javax.swing.JLabel();
        salir = new javax.swing.JLabel();
        reportes = new javax.swing.JLabel();
        capacitaciones = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clientebuscado = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_tareas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cuentaproceso = new javax.swing.JLabel();
        cuentainicio = new javax.swing.JLabel();
        cuentapendientes = new javax.swing.JLabel();
        cuentatotal = new javax.swing.JLabel();
        JP_pendientes = new com.Graficos.Principal.TareasPendiente();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SISCONTROLAB");
        setBackground(new java.awt.Color(0, 153, 153));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        clientes.setBackground(new java.awt.Color(0, 153, 153));
        clientes.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        clientes.setForeground(new java.awt.Color(255, 255, 255));
        clientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/cliente.png"))); // NOI18N
        clientes.setText("Clientes");
        clientes.setToolTipText("Creación de Clientes");
        clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clientes.setOpaque(true);
        clientes.setPreferredSize(new java.awt.Dimension(80, 83));
        clientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesMouseClicked(evt);
            }
        });

        tareas.setBackground(new java.awt.Color(0, 153, 153));
        tareas.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        tareas.setForeground(new java.awt.Color(255, 255, 255));
        tareas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tareas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/tarea.png"))); // NOI18N
        tareas.setText("Tarea");
        tareas.setToolTipText("Creación de Tareas");
        tareas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tareas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tareas.setOpaque(true);
        tareas.setPreferredSize(new java.awt.Dimension(80, 83));
        tareas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tareasMouseClicked(evt);
            }
        });

        listado.setBackground(new java.awt.Color(0, 153, 153));
        listado.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        listado.setForeground(new java.awt.Color(255, 255, 255));
        listado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/lista-tareas.png"))); // NOI18N
        listado.setText("ListaTareas");
        listado.setToolTipText("Lista de Tareas");
        listado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        listado.setMaximumSize(new java.awt.Dimension(64, 88));
        listado.setMinimumSize(new java.awt.Dimension(64, 88));
        listado.setOpaque(true);
        listado.setPreferredSize(new java.awt.Dimension(80, 83));
        listado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        listado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listadoMouseClicked(evt);
            }
        });

        salir.setBackground(new java.awt.Color(0, 153, 153));
        salir.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        salir.setText("Salir");
        salir.setToolTipText("Salir");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setOpaque(true);
        salir.setPreferredSize(new java.awt.Dimension(80, 83));
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
            }
        });

        reportes.setBackground(new java.awt.Color(0, 153, 153));
        reportes.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        reportes.setForeground(new java.awt.Color(255, 255, 255));
        reportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/reportes.png"))); // NOI18N
        reportes.setText("Reportes");
        reportes.setToolTipText("Lista de Tareas");
        reportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportes.setOpaque(true);
        reportes.setPreferredSize(new java.awt.Dimension(80, 83));
        reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportesMouseClicked(evt);
            }
        });

        capacitaciones.setBackground(new java.awt.Color(0, 153, 153));
        capacitaciones.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        capacitaciones.setForeground(new java.awt.Color(255, 255, 255));
        capacitaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        capacitaciones.setText("Capacitaciones");
        capacitaciones.setToolTipText("Control Capacitaciones");
        capacitaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        capacitaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        capacitaciones.setOpaque(true);
        capacitaciones.setPreferredSize(new java.awt.Dimension(80, 83));
        capacitaciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        capacitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                capacitacionesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clientes, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tareas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(capacitaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(tareas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(listado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(capacitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre Cliente:");

        clientebuscado.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        clientebuscado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buscar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/buscar.png"))); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientebuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscar)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(clientebuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(null);

        panel_tareas.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(panel_tareas);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        cuentaproceso.setBackground(new java.awt.Color(0, 153, 153));
        cuentaproceso.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        cuentaproceso.setForeground(new java.awt.Color(255, 255, 255));
        cuentaproceso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentaproceso.setText("#. tareas en proceso.");
        cuentaproceso.setOpaque(true);

        cuentainicio.setBackground(new java.awt.Color(0, 153, 153));
        cuentainicio.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        cuentainicio.setForeground(new java.awt.Color(255, 255, 255));
        cuentainicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentainicio.setText("#. tareas en inicio.");
        cuentainicio.setOpaque(true);

        cuentapendientes.setBackground(new java.awt.Color(0, 153, 153));
        cuentapendientes.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        cuentapendientes.setForeground(new java.awt.Color(255, 255, 255));
        cuentapendientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentapendientes.setText("#. tareas pendientes.");
        cuentapendientes.setOpaque(true);

        cuentatotal.setBackground(new java.awt.Color(0, 153, 153));
        cuentatotal.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        cuentatotal.setForeground(new java.awt.Color(255, 255, 255));
        cuentatotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentatotal.setText("#. tareas pendientes.");
        cuentatotal.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(cuentaproceso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(cuentainicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(cuentapendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(cuentatotal))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cuentaproceso)
                .addComponent(cuentainicio))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cuentatotal)
                .addComponent(cuentapendientes))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JP_pendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JP_pendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesMouseClicked
        try {
            visualizarModuloClientes();
            modeloUno.actualizarListadoClientes();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_clientesMouseClicked

    private void visualizarModuloClientes() throws DAOException {
        Clientes cliente = new Clientes(this, true, manager);
        cliente.setLocationRelativeTo(this);
        cliente.setVisible(true);
    }

    private void tareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tareasMouseClicked
        try {
            visualizarModuloCrearTareas();
            visualizarTareasPendientes();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_tareasMouseClicked

    private void visualizarModuloCrearTareas() throws DAOException {
        DatosTarea tarea = new DatosTarea(this, true, manager);
        tarea.setLocationRelativeTo(this);
        tarea.setVisible(true);
    }

    private void listadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadoMouseClicked
        try {
            visualizarModuloListadoTareas();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al abrir historico de Tareas");
        }
    }//GEN-LAST:event_listadoMouseClicked

    private void visualizarModuloListadoTareas() throws DAOException {
        HistorioTareas lista = new HistorioTareas(this);
        lista.setLocationRelativeTo(this);
        lista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lista.setVisible(true);
    }

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        modeloUno.actualizarListadoClientes();
        System.exit(0);
    }//GEN-LAST:event_salirMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        modeloUno.actualizarListadoClientes();
    }//GEN-LAST:event_formWindowClosing

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        clientebuscado.requestFocus();
    }//GEN-LAST:event_buscarActionPerformed

    private void reportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesMouseClicked
        
    }//GEN-LAST:event_reportesMouseClicked

    private void capacitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capacitacionesMouseClicked

    }//GEN-LAST:event_capacitacionesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Graficos.Principal.TareasPendiente JP_pendientes;
    private javax.swing.JButton buscar;
    private javax.swing.JLabel capacitaciones;
    private javax.swing.JTextField clientebuscado;
    private javax.swing.JLabel clientes;
    private javax.swing.JLabel cuentainicio;
    private javax.swing.JLabel cuentapendientes;
    private javax.swing.JLabel cuentaproceso;
    private javax.swing.JLabel cuentatotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listado;
    private javax.swing.JPanel panel_tareas;
    private javax.swing.JLabel reportes;
    private javax.swing.JLabel salir;
    private javax.swing.JLabel tareas;
    // End of variables declaration//GEN-END:variables

}
