package com.Graficos.Tarea;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ModificaTiempos extends javax.swing.JDialog {

    private DatosTarea tarea;

    public ModificaTiempos(java.awt.Frame parent, boolean modal, DatosTarea tarea) {
        super(parent, modal);
        initComponents();
        this.tarea = tarea;
        iniciarSpinner();
    }

    private void iniciarSpinner() {

        horas.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        minutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        segundos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        milisegundos.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        horas.addChangeListener(new accionesSpinner());
        minutos.addChangeListener(new accionesSpinner());
        segundos.addChangeListener(new accionesSpinner());
        milisegundos.addChangeListener(new accionesSpinner());

        String[] tiempo = tarea.getTarea().getDuracionTarea().split(":");
        horas.setValue(Integer.parseInt(tiempo[0]));
        minutos.setValue(Integer.parseInt(tiempo[1]));
        segundos.setValue(Integer.parseInt(tiempo[2]));
        milisegundos.setValue(Integer.parseInt(tiempo[3]));
        cronometraje.setText(tarea.getTarea().getDuracionTarea());

    }

    private class accionesSpinner implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            cronometraje.setText(
                    (Integer.parseInt(horas.getValue().toString()) <= 9 ? "0" : "") + horas.getValue() + ":"
                    + (Integer.parseInt(minutos.getValue().toString()) <= 9 ? "0" : "") + minutos.getValue() + ":"
                    + (Integer.parseInt(segundos.getValue().toString()) <= 9 ? "0" : "") + segundos.getValue() + ":"
                    + (Integer.parseInt(milisegundos.getValue().toString()) <= 9 ? "0" : "") + milisegundos.getValue());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cronometraje = new javax.swing.JLabel();
        aceptar = new javax.swing.JButton();
        horas = new javax.swing.JSpinner();
        minutos = new javax.swing.JSpinner();
        segundos = new javax.swing.JSpinner();
        milisegundos = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        cronometraje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cronometraje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cronometraje.setText("00:00:00:00");

        aceptar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/guardar.png"))); // NOI18N
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        horas.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        minutos.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        segundos.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        milisegundos.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HH");

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MM");

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SS");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MLSG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cronometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(horas, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minutos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(segundos, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(milisegundos)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cronometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(segundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(milisegundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        tarea.getTarea().setDuracionTarea(cronometraje.getText());
        dispose();
    }//GEN-LAST:event_aceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel cronometraje;
    private javax.swing.JSpinner horas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner milisegundos;
    private javax.swing.JSpinner minutos;
    private javax.swing.JSpinner segundos;
    // End of variables declaration//GEN-END:variables
}
