package com.Graficos.Cliente;

import com.DAO.Recursos.Validaciones;
import com.Modelos.Combos.ParametrosComboModel;
import com.modelo.Cliente;
import com.modelo.Parametros;

public class DatosCliente extends javax.swing.JPanel{

    private Cliente cliente;
    private boolean editable;
    private ParametrosComboModel modeloTipoCliente, modeloTipoSistema;
    private Validaciones camposValidados;

    public DatosCliente() {
        initComponents();
        modeloTipoCliente = new ParametrosComboModel();
        tipo_cliente.setModel(modeloTipoCliente);
        modeloTipoCliente.actualizarCombo(ParametrosComboModel.TIPO_CLIENTE);
       
        modeloTipoSistema = new ParametrosComboModel();
        tipo_sistema.setModel(modeloTipoSistema);
        modeloTipoSistema.actualizarCombo(ParametrosComboModel.TIPO_SISTEMA);
        validarCampos();
    }

    public void cargarDatos() {
        if (cliente != null) {
            nombre.setText(cliente.getNombre());
            telefono.setText(cliente.getTelefono());
            
            tipo_cliente.setSelectedItem((Parametros)Parametros.devuelveValorParametro(cliente.gettCliente(), Parametros.DEVUELVE_PARAMETRO));
            tipo_sistema.setSelectedItem((Parametros)Parametros.devuelveValorParametro(cliente.getId_sistema(), Parametros.DEVUELVE_PARAMETRO));
            contacto.setText(cliente.getContacto());
            if (cliente.getEstado() == Parametros.ACTIVO.getId()) {
                EActivo.setSelected(true);
            } else {
                EInactivo.setSelected(true);
            }
        } else {
            nombre.setText("");
            telefono.setText("");
            EActivo.setSelected(true);
            EInactivo.setSelected(false);
            if (editable) {
                nombre.requestFocus();
            }
            contacto.setText("");
        }
    }

    public void guardarDatos() {
        if (cliente == null) {
            cliente = new Cliente();
        }

        cliente.setNombre(nombre.getText());
        cliente.setTelefono(telefono.getText());
        cliente.settCliente(tipo_cliente.getItemAt(tipo_cliente.getSelectedIndex()).getId());
        cliente.setId_sistema(tipo_sistema.getItemAt(tipo_sistema.getSelectedIndex()).getId());
        cliente.setContacto(contacto.getText());
        if (EActivo.isSelected()) {
            cliente.setEstado(Parametros.ACTIVO.getId());
        } else {
            cliente.setEstado(Parametros.INACTIVO.getId());
        }
    }

    private void validarCampos() {
        camposValidados = new Validaciones(this);
        camposValidados.agregarCampoParaValidar(nombre);
        camposValidados.agregarCampoParaValidar(telefono);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        nombre.setEnabled(editable);
        telefono.setEnabled(editable);
        tipo_cliente.setEnabled(editable);
        tipo_sistema.setEnabled(editable);
        EActivo.setEnabled(editable);
        EInactivo.setEnabled(editable);
        contacto.setEnabled(editable);
    }

    public boolean isEditable() {
        return editable;
    }

    public DatosCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Validaciones getCamposValidados() {
        return camposValidados;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoEstado = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tipo_cliente = new javax.swing.JComboBox<>();
        nombre = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        estado = new javax.swing.JPanel();
        EActivo = new javax.swing.JRadioButton();
        EInactivo = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        tipo_sistema = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        contacto = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOMBRE CLIENTE");

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TELEFONO");

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TIPO CLIENTE");

        estado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ESTADO DEL CLIENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 12))); // NOI18N

        GrupoEstado.add(EActivo);
        EActivo.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        EActivo.setText("ACTIVO");

        GrupoEstado.add(EInactivo);
        EInactivo.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        EInactivo.setText("INACTIVO");

        javax.swing.GroupLayout estadoLayout = new javax.swing.GroupLayout(estado);
        estado.setLayout(estadoLayout);
        estadoLayout.setHorizontalGroup(
            estadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estadoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(EActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62)
                .addComponent(EInactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60))
        );
        estadoLayout.setVerticalGroup(
            estadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estadoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(estadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EActivo)
                    .addComponent(EInactivo))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TIPO SISTEMA");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NOMBRE CONTACTO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo_sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo_sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton EActivo;
    private javax.swing.JRadioButton EInactivo;
    private javax.swing.ButtonGroup GrupoEstado;
    private javax.swing.JTextField contacto;
    private javax.swing.JPanel estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox<Parametros> tipo_cliente;
    private javax.swing.JComboBox<Parametros> tipo_sistema;
    // End of variables declaration//GEN-END:variables

}
