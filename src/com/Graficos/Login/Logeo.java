package com.Graficos.Login;

import com.DAO.Recursos.Validaciones;
import com.Controladores.EnrutamientoServidor;
import com.DAO.DAOManager;
import com.DAO.Recursos.GestionarRecursos;
import com.Graficos.Principal.MenuPrincipal;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Logeo extends javax.swing.JDialog {

    private final EnrutamientoServidor enruta;
    private DAOManager manager;
    private Validaciones validaCampos;
    private Frame padre;

    public Logeo(java.awt.Frame padre, boolean modal, EnrutamientoServidor enruta, DAOManager manager) {
        super(padre, modal);
        initComponents();
        this.enruta = enruta;
        this.manager = manager;
        this.padre = padre;
        validaCampos = new Validaciones(this);
        ingresar.addMouseListener(new accionesRaton());
        ingresar.addKeyListener(new accionesTeclado());
        iniciarServicios();
        agregarValidaciones();
    }

    private void iniciarServicios() {
        
        Object [] opciones = {1, 2};
        try {
            enruta.leerArchivoEnrutador();
            int opcion = JOptionPane.showOptionDialog(this, "Con que Gestor quiere conectarse?", "Conexion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            if (!manager.iniciarConexion(opcion+1)) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error en la conexión verifique los datos", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                verificaDireccionamiento();
                dispose();
            }
        } catch (SQLException ex) {
            
            GestionarRecursos.propagarError(ex);
        }

    }

    private void verificaDireccionamiento() {
        CrearDireccionamiento direccionamiento = new CrearDireccionamiento(null, true, enruta);
        direccionamiento.setLocationRelativeTo(null);
        direccionamiento.cargarDatos();
        direccionamiento.setVisible(true);
    }

    private void agregarValidaciones() {
        validaCampos.agregarCampoParaValidar(usuario);
        validaCampos.agregarCampoParaValidar(contrasenia);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        contrasenia = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMCONTROLLAB V1");
        setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/login.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INICIO SESION");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONTRASEÑA");

        usuario.setBackground(new java.awt.Color(0, 153, 153));
        usuario.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.setBorder(null);
        usuario.setOpaque(false);

        contrasenia.setBackground(new java.awt.Color(0, 153, 153));
        contrasenia.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        contrasenia.setForeground(new java.awt.Color(255, 255, 255));
        contrasenia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contrasenia.setBorder(null);
        contrasenia.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("USUARIO");

        ingresar.setBackground(new java.awt.Color(0, 153, 153));
        ingresar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        ingresar.setForeground(new java.awt.Color(255, 255, 255));
        ingresar.setText("Ingresar");
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contrasenia, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(usuario)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(89, 89, 89))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        padre.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void ingresoASistema() {

        if (validaCampos.validaCampos()) {

            if (compruebaAcceso()) {
                padre.dispose();
                MenuPrincipal menu = new MenuPrincipal(manager);
                menu.setLocationRelativeTo(null);
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                menu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error en Usuario y/o Contraseña, Intente de nuevo", "Login", JOptionPane.ERROR_MESSAGE);
                usuario.requestFocus();
                usuario.setText("");
                contrasenia.setText("");
            }
        } else {
            validaCampos.devuelveMensaje(Validaciones.CAMPOS_VALIDADO);
        }
    }

    private boolean compruebaAcceso() {
        return usuario.getText().compareToIgnoreCase("a") == 0
                && new String(contrasenia.getPassword()).compareToIgnoreCase("a") == 0;
    }

    private class accionesTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ingresoASistema();
            }
        }

    }

    private class accionesRaton extends MouseAdapter {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            ingresoASistema();
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contrasenia;
    private javax.swing.JButton ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
