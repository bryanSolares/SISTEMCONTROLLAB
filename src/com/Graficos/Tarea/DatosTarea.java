package com.Graficos.Tarea;

import com.DAO.Recursos.Validaciones;
import com.DAO.DAOManager;
import com.DAO.Recursos.Animaciones;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.Modelos.Combos.ClientesComboModel;
import com.Modelos.Combos.ParametrosComboModel;
import com.Modelos.Tablas.ModeloTareasDetalle;
import com.modelo.Cliente;
import com.modelo.Parametros;
import com.modelo.Tarea;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatosTarea extends javax.swing.JDialog {

    private ClientesComboModel modeloComboCliente;
    private ParametrosComboModel modeloPrioridad, estadoModelo, modeloArea;
    private final DAOManager manager;
    private Tarea tarea;
    private boolean editable;
    private Validaciones camposValidados;
    private ModeloTareasDetalle modeloTablaDetalle;
    private DefaultTableModel modeloPorDefecto;
    private Tarea.TareaDetalle detalle;

    public DatosTarea(java.awt.Frame parent, boolean modal, DAOManager manager) throws DAOException {
        super(parent, modal);
        this.manager = manager;
        initComponents();
        iniciar();
        agregarModelosACombos();

        modeloTablaDetalle = new ModeloTareasDetalle(manager.getDAOTareas());
        modeloTablaDetalle.actualizarModelo();
        tabla.setModel(modeloTablaDetalle);

        validarCampos();
        scroll_descripcion.setVisible(false);
    }

    private void iniciar() {
        fecha_i.setDate(new Date());
        fecha_f.setDate(new Date());
        modifica_tiempo.addMouseListener(new Animaciones(modifica_tiempo));
    }

    private void agregarModelosACombos() throws DAOException {

        modeloPrioridad = new ParametrosComboModel();
        estadoModelo = new ParametrosComboModel();
        modeloArea = new ParametrosComboModel();
        modeloComboCliente = new ClientesComboModel(manager.getDAOClientes());
        modeloComboCliente.filtraClienteActivos(true);
        modeloComboCliente.actualizarModelo();
        clientes.setModel(modeloComboCliente);
        prioridad.setModel(modeloPrioridad);
        modeloPrioridad.actualizarCombo(ParametrosComboModel.TIPO_PRIORIDAD);
        estado.setModel(estadoModelo);
        estadoModelo.actualizarCombo(ParametrosComboModel.TIPO_ESTADO_TAREA);
        area.setModel(modeloArea);
        modeloArea.actualizarCombo(ParametrosComboModel.TIPO_REPONSABLE_SOPORTE);

    }

    public void guardarDatos() {
        if (tarea == null) {
            tarea = new Tarea();
        }

        tarea.setTitulo(nombre.getText());
        tarea.setCliente(clientes.getItemAt(clientes.getSelectedIndex()).getId());
        tarea.setDescripcion(descripcion.getText());
        tarea.setPrioridad(prioridad.getItemAt(prioridad.getSelectedIndex()).getId());
        tarea.setEstado(estado.getItemAt(estado.getSelectedIndex()).getId());
        tarea.setFechaInicio(fecha_i.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        tarea.setFechaFin(fecha_f.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        tarea.setAreaResponsable(area.getItemAt(area.getSelectedIndex()).getId());
        tarea.getListaDetalles().clear();
        tarea.setListaDetalles(modeloTablaDetalle.getDataElements());
        

        detalle = null;

    }

    public void cargarDatos() throws DAOException {

        if (tarea != null) {
            nombre.setText(tarea.getTitulo());
            clientes.setSelectedItem(devuelveCliente(tarea));
            descripcion.setText(tarea.getDescripcion());
            prioridad.setSelectedItem((Parametros) Parametros.devuelveValorParametro(tarea.getPrioridad(), Parametros.DEVUELVE_PARAMETRO));
            estado.setSelectedItem((Parametros) Parametros.devuelveValorParametro(tarea.getEstado(), Parametros.DEVUELVE_PARAMETRO));
            area.setSelectedItem((Parametros) Parametros.devuelveValorParametro(tarea.getAreaResponsable(), Parametros.DEVUELVE_PARAMETRO));
            duracionTarea.setText(convierteTiempo(tarea.getDuracionTarea()));
            fecha_i.setDate(java.sql.Date.valueOf(tarea.getFechaInicio().toLocalDate()));
            fecha_f.setDate(java.sql.Date.valueOf(tarea.getFechaFin().toLocalDate()));

            modeloTablaDetalle.setTarea(tarea.getId());
            guardar.setText("Guardar Tarea");

        } else {
            nombre.setText("");
            descripcion.setText("");
            duracionTarea.setText("TIEMPO ACTUAL: ");
            fecha_i.setDate(new Date());
            fecha_f.setDate(new Date());
            guardar.setText("Nueva Tarea");
            modeloTablaDetalle.setTarea(null);
            agregarModelosACombos();
        }
        modeloTablaDetalle.actualizarModelo();

    }

    private void manipulaTarea() {
        try {
            guardarDatos();

            if (tarea.getId() == null) {
                if (!verificarEstadoFinalizado()) {
                    manager.getDAOTareas().crear(tarea);
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede crear una tarea con estado Finalizado", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                if (!verificaReinicio()) {

                    if (!verificarEstadoFinalizado()) {
                        manager.getDAOTareas().editar(tarea);
                    } else {
                        if (fecha_f.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) == 0) {
                            if (JOptionPane.showConfirmDialog(this, "Seguro que quiere finalizar la Tarea, \n No podrá modificar de nuevo",
                                    "Finalizar Tarea", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                                manager.getDAOTareas().editar(tarea);
                            } else {
                                return;
                            }
                        } else {
                            fecha_f.setBackground(Color.red.brighter());
                            if (JOptionPane.showConfirmDialog(this, "Confirme si realmente quiere finalizar la tarea fuera de la fecha actual, \n No podrá modificar de nuevo",
                                    "Finalizar Tarea", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                                fecha_f.setBackground(fecha_i.getBackground());
                                manager.getDAOTareas().editar(tarea);
                            } else {
                                fecha_f.setBackground(fecha_i.getBackground());
                                return;
                            }
                        }
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(this, "Actualmente tiene un tiempo acumulado, \n Si aplica la modificación"
                            + "reiniciará los valores. \n Está seguro de reiniciar?",
                            "Modificación de Tarea", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        reiniciarTarea();
                        manager.getDAOTareas().editar(tarea);
                    } else {
                        return;
                    }
                }
            }
            tarea = null;
            cargarDatos();
            setEditable(false);
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void reiniciarTarea() {
        tarea.setDuracionTarea("00:00:00:00");
    }

    private boolean verificarEstadoFinalizado() {
        return estado.getSelectedItem().toString().equals(Parametros.FINAL.toString());
    }

    private boolean verificaReinicio() {
        return estado.getSelectedItem().toString().equals(Parametros.INICIAL.toString()) && !tarea.getDuracionTarea().equals("00:00:00:00");
    }

    private Cliente devuelveCliente(Tarea tarea) throws DAOException {
        return manager.getDAOClientes().buscarUno(tarea.getCliente());
    }

    private void validarCampos() {
        camposValidados = new Validaciones(this);
        camposValidados.agregarCampoParaValidar(nombre);
        camposValidados.agregarTablasParaValidar(modeloTablaDetalle);
        //camposValidados.agregarCampoParaValidar(descripcion);
    }

    private String convierteTiempo(String tiempo) {
        String[] ti = tiempo.split(":");
        return "Horas: " + ti[0] + " Minutos: " + ti[1] + " Segundos: " + ti[2] + " Milisegundos: " + ti[3];
    }

    public void setHabilitaTodo(boolean habilitaTodo) {
        nombre.setEnabled(habilitaTodo);
        clientes.setEnabled(habilitaTodo);
        descripcion.setEnabled(habilitaTodo);
        prioridad.setEnabled(habilitaTodo);
        estado.setEnabled(habilitaTodo);
        area.setEnabled(habilitaTodo);
        duracionTarea.setEnabled(habilitaTodo);
        fecha_i.setEnabled(habilitaTodo);
        fecha_f.setEnabled(habilitaTodo);
        modifica_tiempo.setEnabled(habilitaTodo);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {

        nombre.setEnabled(editable);
        if (tarea != null) {
            clientes.setEnabled(!editable);
            modifica_tiempo.setEnabled(editable);
        } else {
            clientes.setEnabled(editable);
            modifica_tiempo.setEnabled(!editable);
        }
        descripcion.setEnabled(editable);
        prioridad.setEnabled(editable);
        estado.setEnabled(editable);
        area.setEnabled(editable);
        fecha_i.setEnabled(editable);
        fecha_f.setEnabled(editable);
        dato.setEnabled(editable);
        agregar.setEnabled(editable);
        eliminar.setEnabled(editable);
        tabla.setEnabled(editable);
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        texto_descripcion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        clientes = new javax.swing.JComboBox<>();
        prioridad = new javax.swing.JComboBox<>();
        estado = new javax.swing.JComboBox<>();
        duracionTarea = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        fecha_i = new com.toedter.calendar.JDateChooser();
        fecha_f = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        area = new javax.swing.JComboBox<>();
        modifica_tiempo = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        dato = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        scroll_descripcion = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cliente:");

        texto_descripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        texto_descripcion.setText("Tareas:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Prioridad:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tiempo Actual:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Fecha Inicio:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Fecha Fin:");

        nombre.setEnabled(false);

        clientes.setEnabled(false);

        prioridad.setEnabled(false);

        estado.setEnabled(false);

        duracionTarea.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        duracionTarea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duracionTarea.setText("Dias: Minutos: Segundos:");
        duracionTarea.setEnabled(false);

        guardar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/guardar.png"))); // NOI18N
        guardar.setText("Nueva Tarea");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        salir.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        salir.setText("Salir");
        salir.setMaximumSize(new java.awt.Dimension(139, 73));
        salir.setMinimumSize(new java.awt.Dimension(139, 73));
        salir.setPreferredSize(new java.awt.Dimension(139, 73));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        fecha_i.setDateFormatString("dd/MM/yyyy");
        fecha_i.setEnabled(false);
        fecha_i.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        fecha_f.setDateFormatString("dd/MM/yyyy");
        fecha_f.setEnabled(false);
        fecha_f.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Responsable:");

        area.setEnabled(false);

        modifica_tiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/tiempo.png"))); // NOI18N
        modifica_tiempo.setToolTipText("Modificar Tiempos");
        modifica_tiempo.setEnabled(false);
        modifica_tiempo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modifica_tiempoMouseClicked(evt);
            }
        });

        agregar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 14)); // NOI18N
        agregar.setText("+");
        agregar.setEnabled(false);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        eliminar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 14)); // NOI18N
        eliminar.setText("-");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        dato.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(400, 400, 400)
                        .addComponent(modifica_tiempo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(76, 76, 76)
                                .addComponent(fecha_f, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(58, 58, 58)
                                .addComponent(fecha_i, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(texto_descripcion)
                                    .addComponent(jLabel5))
                                .addGap(78, 78, 78)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prioridad, 0, 350, Short.MAX_VALUE)
                                    .addComponent(clientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dato)))
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(duracionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_descripcion)
                    .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminar)
                    .addComponent(dato, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(duracionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(modifica_tiempo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(67, 67, 67))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fecha_i, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fecha_f, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Registro de Clientes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Detalle de Tareas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setEnabled(false);
        jScrollPane2.setViewportView(tabla);

        descripcion.setColumns(1);
        descripcion.setLineWrap(true);
        descripcion.setRows(1);
        descripcion.setTabSize(1);
        descripcion.setWrapStyleWord(true);
        descripcion.setDropMode(javax.swing.DropMode.INSERT);
        descripcion.setEnabled(false);
        scroll_descripcion.setViewportView(descripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scroll_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        if (tarea != null) {
            try {
                manager.getDAOTareas().actualizarTiempoTarea(tarea);
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex, "Error al modificar tiempo al cerrar DatosTarea");
            }
        }
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (guardar.getText().compareToIgnoreCase("Guardar Tarea") == 0) {
            if (camposValidados.validaCampos() && camposValidados.validaTablas()) {
                manipulaTarea();
            } else {
                if (!camposValidados.validaCampos()) {
                    camposValidados.devuelveMensaje(Validaciones.CAMPOS_VALIDADO);
                } else {
                    camposValidados.devuelveMensaje(Validaciones.TABLAS_VALIDADAS);
                }
            }
        } else {
            setEditable(true);
            guardar.setText("Guardar Tarea");
            nombre.requestFocus();
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void modifica_tiempoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifica_tiempoMouseClicked
        cargarCronometrajeParaModificar();
    }//GEN-LAST:event_modifica_tiempoMouseClicked

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed

        if (!dato.getText().equalsIgnoreCase("")) {
            String descrip = dato.getText();
            dato.setText("");
            dato.requestFocus();

            if (detalle == null) {
                detalle = new Tarea().new TareaDetalle();
                detalle.setIdDetalle(modeloTablaDetalle.getRowCount());
            }

            detalle.setDescripcion(descrip);
            modeloTablaDetalle.addElementToData(detalle);
            detalle = null;
        }else{
            JOptionPane.showMessageDialog(this, "No puede agregar elementos vacios","Agregar Detalle",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

        if (tabla.getModel().getRowCount() > 0) {
            if (tabla.getSelectedRow() >= 0) {
                int fila = tabla.getSelectedRow();
                detalle = modeloTablaDetalle.getElementByIndex(fila);
                modeloTablaDetalle.removeElementWithIndex(fila);
            } else {
                detalle = modeloTablaDetalle.getElementByIndex(tabla.getRowCount()-1);
                modeloTablaDetalle.removeElementWithIndex(modeloTablaDetalle.getRowCount()-1);
            }
            dato.setText(detalle.getDescripcion());
        }else{
            JOptionPane.showMessageDialog(this, "No existen elementos para eliminar","Error al eliminar",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void cargarCronometrajeParaModificar() {
        try {
            if (tarea.getEstado() != Parametros.INICIAL.getId()) {
                ModificaTiempos mt = new ModificaTiempos(null, true, this);
                mt.setLocationRelativeTo(this);
                mt.setVisible(true);
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "No puede actualizar tiempos de tareas no iniciadas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error en método cargarCronometrajeParaModificar " + getClass());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JComboBox<Parametros> area;
    private javax.swing.JComboBox<Cliente> clientes;
    private javax.swing.JTextField dato;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel duracionTarea;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<Parametros> estado;
    private com.toedter.calendar.JDateChooser fecha_f;
    private com.toedter.calendar.JDateChooser fecha_i;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel modifica_tiempo;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<Parametros> prioridad;
    private javax.swing.JButton salir;
    private javax.swing.JScrollPane scroll_descripcion;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel texto_descripcion;
    // End of variables declaration//GEN-END:variables

}
