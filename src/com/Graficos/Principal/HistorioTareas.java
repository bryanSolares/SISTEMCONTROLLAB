package com.Graficos.Principal;

import com.DAO.DAOManager;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Graficos.Tarea.DatosTarea;
import com.Modelos.Combos.ClientesComboModel;
import com.Modelos.Tablas.ListaTareasTableModel;
import com.modelo.Cliente;
import com.modelo.Parametros;
import com.modelo.Tarea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HistorioTareas extends javax.swing.JFrame {

    private DAOManager manager;
    private ListaTareasTableModel modeloTabla;
    private ClientesComboModel modeloCombo;
    private TableRowSorter<TableModel> modeloOrdenado;
    private MenuPrincipal menu;

    public HistorioTareas(MenuPrincipal menu) throws DAOException {
        initComponents();
        this.manager = menu.getManager();
        this.menu = menu;
        iniciar();
        actualizaModelos();
        procedimiento();
    }

    private void iniciar() {
        try {
            setIconImage(new ImageIcon(getClass().getResource(manager.rutaImagenProyecto())).getImage());
        } catch (Exception ex) {
            GestionarRecursos.propagarError(ex, "No se ha encontrado la imagen del proyecto favor verificar");
        }
        modeloTabla = new ListaTareasTableModel(manager.getDAOTareas(), manager.getDAOClientes());
        modeloCombo = new ClientesComboModel(manager.getDAOClientes());
        fecha_ini.setDate(java.sql.Date.valueOf(LocalDate.now().minusDays(5)));
        fecha_fin.setDate(java.sql.Date.valueOf(LocalDate.now()));

        tareaslista.getSelectionModel().addListSelectionListener(e -> {
            if (tareaslista.getSelectedRow() != -1) {
                boolean seleccionValida = (tareaslista.getValueAt(tareaslista.getSelectedRow(), 5) == Parametros.FINAL.toString());
                activar.setEnabled(seleccionValida);
                modificar.setEnabled(seleccionValida);
            }
            duplicar.setEnabled(tareaslista.getSelectedRow() != -1);
        });

//        procedimiento();
        modeloTabla.estableFiltros(null, LocalDate.now().minusDays(5), LocalDate.now());
    }

    private void procedimiento() {
        tareaslista.addMouseListener(new MouseAdapter() {

            List<String> listaTiempos = new ArrayList<>();

            @Override
            public void mouseReleased(MouseEvent e) {
                listaTiempos.clear();
                List<Integer> listaSeleccion = Arrays.stream(tareaslista.getSelectedRows()).boxed().collect(Collectors.toList());
                listaSeleccion.forEach(n -> {
                    listaTiempos.add((String) tareaslista.getValueAt(n, 6));
                });

                int horas = listaTiempos.stream().map(t -> t.split(":")).mapToInt(t -> Integer.parseInt(t[0])).sum();
                int minutos = listaTiempos.stream().map(t -> t.split(":")).mapToInt(t -> Integer.parseInt(t[1])).sum();
                int segundos = listaTiempos.stream().map(t -> t.split(":")).mapToInt(t -> Integer.parseInt(t[2])).sum();

                int tiempoEnSegudos = (minutos * 60) + (horas * 3600) + segundos;

                int horaReales = tiempoEnSegudos / 3600;
                int minutosReales = (tiempoEnSegudos - (3600 * horaReales)) / 60;
                int segundoReales = tiempoEnSegudos - ((horaReales * 3600) + (minutosReales * 60));

                cuentahoras.setText("Tiempo invertido: " + horaReales + " Horas " + minutosReales+ " Minutos " + segundoReales + " Segundos");
            }
        });
    }

    private void actualizaModelos() {
        try {

            modeloTabla.fireTableDataChanged();
            modeloTabla.actualizarModelo();
            if (clientes.getSelectedItem() == null) {
                modeloCombo.actualizarModelo();
                clientes.setModel(modeloCombo);
            }
            tareaslista.setModel(modeloTabla);
            modeloOrdenado = new TableRowSorter<>(tareaslista.getModel());
            tareaslista.setRowSorter(modeloOrdenado);
            modeloCombo.filtraClienteActivos(false);
            contabilizarRegistros();
            activar.setEnabled(false);
            modificar.setEnabled(false);
            duplicar.setEnabled(false);

            List<RowSorter.SortKey> ordenPorDefecto = new ArrayList<>();
            ordenPorDefecto.add(new RowSorter.SortKey(8, SortOrder.DESCENDING));
            modeloOrdenado.setSortKeys(ordenPorDefecto);

        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al actualizar Modelo en Historico de Tareas");
        }
    }

    public void actualizaTabla() throws DAOException {

        if (validaCamposFechas()) {

            modeloTabla.estableFiltros(
                    (clientes.getSelectedItem() == null) ? null : clientes.getItemAt(clientes.getSelectedIndex()).getId(),
                    (fecha_ini.getDate() != null) ? fecha_ini.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null,
                    (fecha_fin.getDate() != null) ? fecha_fin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);

            actualizaModelos();
        } else {

            JOptionPane.showMessageDialog(this, "No puede hacer la busqueda sin especificar la fecha inicial y final \n"
                    + "o fechas validas.", "Erro en fecha", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clientes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tareaslista = new javax.swing.JTable();
        activar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        cuentaregistros = new javax.swing.JLabel();
        duplicar = new javax.swing.JButton();
        cuentahoras = new javax.swing.JLabel();
        fecha_fin = new com.toedter.calendar.JDateChooser();
        fecha_ini = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tareas Realizadas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel2.setText("Cliente:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel3.setText("Fecha Inicio:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel4.setText("Fecha Fin:");

        buscar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/buscar.png"))); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        tareaslista.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tareaslista);

        activar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/activar.png"))); // NOI18N
        activar.setText("Reactivar Tarea");
        activar.setEnabled(false);
        activar.setMaximumSize(new java.awt.Dimension(161, 59));
        activar.setMinimumSize(new java.awt.Dimension(161, 59));
        activar.setPreferredSize(new java.awt.Dimension(161, 59));
        activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarActionPerformed(evt);
            }
        });

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/editar2.png"))); // NOI18N
        modificar.setText("Modificar Datos");
        modificar.setEnabled(false);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        cuentaregistros.setBackground(new java.awt.Color(0, 153, 153));
        cuentaregistros.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        cuentaregistros.setForeground(new java.awt.Color(0, 153, 153));
        cuentaregistros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentaregistros.setText("#. registros disponibles.");

        duplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/duplicar.png"))); // NOI18N
        duplicar.setText("Duplicar Tarea");
        duplicar.setEnabled(false);
        duplicar.setMaximumSize(new java.awt.Dimension(161, 59));
        duplicar.setMinimumSize(new java.awt.Dimension(161, 59));
        duplicar.setPreferredSize(new java.awt.Dimension(161, 59));
        duplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicarActionPerformed(evt);
            }
        });

        cuentahoras.setBackground(new java.awt.Color(0, 153, 153));
        cuentahoras.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        cuentahoras.setForeground(new java.awt.Color(0, 153, 153));
        cuentahoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuentahoras.setText("Tiempo Invertido:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(35, 35, 35)
                                        .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fecha_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(activar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(duplicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cuentaregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(709, 709, 709)
                                .addComponent(cuentahoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fecha_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(activar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(duplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(buscar))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuentaregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuentahoras, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
            actualizaTabla();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al ejecutar la busqueda");
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarActionPerformed
        if (activarTarea()) {
            JOptionPane.showMessageDialog(this, "Tarea Activada", "Activación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo activar la Tarea Seleccionada", "Activación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_activarActionPerformed

    private boolean activarTarea() {
        try {

            Tarea tarea = devuelveTareaSeleccionada();
            tarea.setEstado(Parametros.PENDIENTE.getId());
            manager.getDAOTareas().editar(tarea);

            actualizaTabla();
            menu.visualizarTareasPendientes();

            return true;

        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al activar Tarea en " + getClass());
            return false;
        }
    }

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        modificarTarea();
    }//GEN-LAST:event_modificarActionPerformed

    private void modificarTarea() {
        try {
            DatosTarea modificador = new DatosTarea(this, true, manager);
            modificador.setTarea(devuelveTareaSeleccionada());
            modificador.cargarDatos();
            modificador.setHabilitaTodo(true);
            modificador.setLocationRelativeTo(this);
            modificador.setVisible(true);

            actualizaTabla();

        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al modificar Tarea en " + getClass());
        }
    }

    private void duplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicarActionPerformed
        if (duplicarTarea()) {
            JOptionPane.showMessageDialog(this, "Tarea Duplicada con Exito", "Duplicar Tarea", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al duplicar Tarea", "Duplicar Tarea", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_duplicarActionPerformed

    private boolean duplicarTarea() {
        try {
            Tarea nueva = devuelveTareaSeleccionada();
            nueva.setEstado(Parametros.INICIAL.getId());
            nueva.setFechaInicio(LocalDateTime.now());
            nueva.setFechaFin(LocalDateTime.now());
            manager.getDAOTareas().crear(nueva);
            actualizaTabla();
            menu.visualizarTareasPendientes();
            return true;
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error en método duplicarTarea " + getClass());
            return false;
        }
    }

    private Tarea devuelveTareaSeleccionada() {
        try {
            Long id = (Long) tareaslista.getValueAt(tareaslista.getSelectedRow(), 0);
            Tarea tarea = manager.getDAOTareas().buscarUno(id);
            return tarea;
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al buscar Tarea en " + getClass());
            return null;
        }
    }

    private boolean validaCamposFechas() {
        if (fecha_ini.getDate() != null && fecha_fin.getDate() == null) {
            return false;
        } else if (fecha_ini.getDate() == null && fecha_fin.getDate() != null) {
            return false;
        } else if ((fecha_ini.getDate() != null && fecha_fin.getDate() != null)
                && (fecha_ini.getDate().after(fecha_fin.getDate()))) {
            return false;
        }
        return true;
    }

    public ListaTareasTableModel getModeloTabla() {
        return modeloTabla;
    }

    private void contabilizarRegistros() {
        cuentaregistros.setText(String.valueOf(tareaslista.getRowCount()) + " Registros.");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activar;
    private javax.swing.JButton buscar;
    private javax.swing.JComboBox<Cliente> clientes;
    private javax.swing.JLabel cuentahoras;
    private javax.swing.JLabel cuentaregistros;
    private javax.swing.JButton duplicar;
    private com.toedter.calendar.JDateChooser fecha_fin;
    private com.toedter.calendar.JDateChooser fecha_ini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificar;
    private javax.swing.JTable tareaslista;
    // End of variables declaration//GEN-END:variables
}
