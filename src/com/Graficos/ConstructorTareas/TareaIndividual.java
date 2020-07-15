package com.Graficos.ConstructorTareas;

import com.DAO.Recursos.Animaciones;
import com.DAO.DAOManager;
import com.DAO.DAOException;
import com.DAO.Recursos.GestionarRecursos;
import com.DAO.Recursos.RetoquesSwing;
import com.Graficos.Principal.MenuPrincipal;
import com.Graficos.Tarea.Cronometro;
import com.Graficos.Tarea.DatosTarea;
import com.modelo.Parametros;
import com.modelo.Tarea;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TareaIndividual extends javax.swing.JPanel implements Comparable<TareaIndividual> {

    private Tarea tarea;
    private final DAOManager manager;
    private final ImageIcon iconoPrioridad;
    private final int escala_imagen_prioridad = 40;
    private final MenuPrincipal menuPrincipal;
    private final String nombreCliente;
    private final String nombreColor;

    public TareaIndividual(Tarea tarea, DAOManager manager, Color color, MenuPrincipal padre, String nombreCliente, String nombreColor) throws DAOException {

        initComponents();
        this.manager = manager;
        this.tarea = tarea;
        this.menuPrincipal = padre;
        this.nombreCliente = nombreCliente;
        this.nombreColor = nombreColor;
        iconoPrioridad = new ImageIcon(getClass().getResource("/com/Iconos/nivel-prioridad.png"));
        iniciar(color);
        cargarDatos();
    }

    private void iniciar(Color color) {
        icono_editar.setBackground(color);
        icono_eliminar.setBackground(color);
        icono_iniciar.setBackground(color);
        icono_pausar.setBackground(color);
        this.setBackground(color);
        iniciarAnimaciones();
        this.setToolTipText(tarea.getDescripcion());
    }

    private void cargarDatos(){
        nombre_tarea.setText(tarea.getTitulo());
        nombre_cliente.setText(nombreCliente);
        estado_actual.setText(Parametros.devuelveValorParametro(tarea.getEstado(), Parametros.DEVUELVE_VALOR).toString());
        cronometro.convierteTiempoACronometro(tarea.getDuracionTarea());
        nombreCol.setText(this.nombreColor);
        establecerPrioridad();
    }

    private void establecerPrioridad() {

        // Prioridades de estrellas 5 - 3 - 1
        switch (tarea.getPrioridad()) {
            case 20:
                for (int i = 0; i < 5; i++) {
                    panelprioridad.add(new JLabel(cargarImagenPrioridad()));
                }
                break;
            case 21:
                for (int i = 0; i < 3; i++) {
                    panelprioridad.add(new JLabel(cargarImagenPrioridad()));
                }
                break;
            case 22:
                for (int i = 0; i < 1; i++) {
                    panelprioridad.add(new JLabel(cargarImagenPrioridad()));
                }
                break;
        }
    }

    private ImageIcon cargarImagenPrioridad() {
        return new ImageIcon(iconoPrioridad.getImage().getScaledInstance(iconoPrioridad.getIconWidth() - escala_imagen_prioridad, iconoPrioridad.getIconHeight() - escala_imagen_prioridad, Image.SCALE_SMOOTH));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        icono = new javax.swing.JLabel();
        nombre_cliente = new javax.swing.JLabel();
        estado_actual = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        icono_editar = new javax.swing.JLabel();
        icono_eliminar = new javax.swing.JLabel();
        panelprioridad = new javax.swing.JPanel();
        prioridad = new javax.swing.JLabel();
        nombre_tarea = new javax.swing.JLabel();
        icono_iniciar = new javax.swing.JLabel();
        icono_pausar = new javax.swing.JLabel();
        cronometro = new com.Graficos.Tarea.Cronometro();
        nombreCol = new javax.swing.JLabel();

        setBackground(new java.awt.Color(188, 136, 191));

        icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/tarea-individual.png"))); // NOI18N

        nombre_cliente.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        nombre_cliente.setForeground(new java.awt.Color(255, 255, 255));
        nombre_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre_cliente.setText("NOMBRE CLIENTE");

        estado_actual.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        estado_actual.setForeground(new java.awt.Color(255, 255, 255));
        estado_actual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estado_actual.setText("ESTADO ACTUAL");

        icono_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/editar2.png"))); // NOI18N
        icono_editar.setToolTipText("Editar Tarea");
        icono_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_editar.setOpaque(true);
        icono_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_editarMouseClicked(evt);
            }
        });

        icono_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/borrar2.png"))); // NOI18N
        icono_eliminar.setToolTipText("Eliminar Tarea");
        icono_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_eliminar.setOpaque(true);
        icono_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_eliminarMouseClicked(evt);
            }
        });

        panelprioridad.setOpaque(false);
        panelprioridad.add(prioridad);

        nombre_tarea.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        nombre_tarea.setForeground(new java.awt.Color(255, 255, 255));
        nombre_tarea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre_tarea.setText("NOMBRE TAREA");

        icono_iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/iniciar.png"))); // NOI18N
        icono_iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_iniciar.setOpaque(true);
        icono_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_iniciarMouseClicked(evt);
            }
        });

        icono_pausar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/pausar.png"))); // NOI18N
        icono_pausar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_pausar.setOpaque(true);
        icono_pausar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_pausarMouseClicked(evt);
            }
        });

        nombreCol.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        nombreCol.setForeground(new java.awt.Color(255, 255, 255));
        nombreCol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreCol.setText("color");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nombreCol)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombre_tarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addComponent(estado_actual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelprioridad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jSeparator1)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(icono_editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(icono_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(icono_iniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(icono_pausar)
                .addGap(10, 10, 10))
            .addComponent(cronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombre_tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreCol)))
                .addGap(4, 4, 4)
                .addComponent(panelprioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono_pausar)
                    .addComponent(icono_editar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icono_eliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icono_iniciar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void icono_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_editarMouseClicked
        try {

            cronometro.pausar();
            DatosTarea detalleTarea = new DatosTarea(menuPrincipal, true, manager);
            tarea.setDuracionTarea(cronometro.getCronometraje());
            detalleTarea.setTarea(tarea);
            detalleTarea.cargarDatos();
            detalleTarea.setEditable(true);
            detalleTarea.setLocationRelativeTo(this);
            detalleTarea.setVisible(true);
            menuPrincipal.visualizarTareasPendientes();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }//GEN-LAST:event_icono_editarMouseClicked

    private void icono_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_eliminarMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Seguro que quiere eliminar este regitros", "Eliminar Tarea",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                menuPrincipal.getListadoTareas().removeIf(t -> t.getTarea().equals(tarea));
                manager.getDAOTareas().borrar(tarea.getId());
                menuPrincipal.visualizarTareasPendientes();
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex, "No se pudo eliminar la Tarea");
            }
        }
    }//GEN-LAST:event_icono_eliminarMouseClicked

    private void icono_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_iniciarMouseClicked
        try {
            if (tarea.getEstado() != Parametros.PROCESO.getId()) {
                cronometro.iniciar();
                tarea.setEstado(Parametros.PROCESO.getId());
                tarea.setDuracionTarea(cronometro.getCronometraje());
                manager.getDAOTareas().editar(tarea);
                menuPrincipal.visualizarTareasPendientes();
            }else{
                JOptionPane.showMessageDialog(this, "No puede inicar una tarea ya iniciada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al iniciar cronometro");
        }
    }//GEN-LAST:event_icono_iniciarMouseClicked

    private void icono_pausarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_pausarMouseClicked
        try {
            if (!"00:00:00:00".equals(cronometro.getCronometraje()) && tarea.getEstado() == Parametros.PROCESO.getId()) {
                cronometro.pausar();
                tarea.setEstado(Parametros.PENDIENTE.getId());
                tarea.setDuracionTarea(cronometro.getCronometraje());
                manager.getDAOTareas().editar(tarea);
                menuPrincipal.visualizarTareasPendientes();
            } else {
                JOptionPane.showMessageDialog(this, "No puede pausar una tarea no iniciada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, "Error al pausar cronometro");
        }
    }//GEN-LAST:event_icono_pausarMouseClicked

    @Override
    public int compareTo(TareaIndividual o) {

        if (this.getTarea().getFechaInicio().isBefore(o.getTarea().getFechaInicio())) {
            return -1;
        } else if (this.getTarea().getFechaInicio().isAfter(o.getTarea().getFechaInicio())) {
            return 1;
        } else {
            return 0;
        }

    }

    public Tarea getTarea() {
        tarea.setDuracionTarea(cronometro.getCronometraje());
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Cronometro getCronometro() {
        return cronometro;
    }
    
    private void iniciarAnimaciones() {
        icono_editar.addMouseListener(new Animaciones(icono_editar));
        icono_eliminar.addMouseListener(new Animaciones(icono_eliminar));
        icono_iniciar.addMouseListener(new Animaciones(icono_iniciar));
        icono_pausar.addMouseListener(new Animaciones(icono_pausar));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Graficos.Tarea.Cronometro cronometro;
    private javax.swing.JLabel estado_actual;
    private javax.swing.JLabel icono;
    private javax.swing.JLabel icono_editar;
    private javax.swing.JLabel icono_eliminar;
    private javax.swing.JLabel icono_iniciar;
    private javax.swing.JLabel icono_pausar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nombreCol;
    private javax.swing.JLabel nombre_cliente;
    private javax.swing.JLabel nombre_tarea;
    private javax.swing.JPanel panelprioridad;
    private javax.swing.JLabel prioridad;
    // End of variables declaration//GEN-END:variables

}
