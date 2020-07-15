package com.Graficos.Tarea;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Cronometro extends javax.swing.JPanel {

    private Timer cronometro;
    private int horas, minutos, segundos, milisegundos;
    private Color color;

    public Cronometro() {
        initComponents();
        cronometro = new Timer(10, acciones);
    }

    private ActionListener acciones = (e) -> {
        milisegundos++;
        if (milisegundos == 100) {
            milisegundos = 0;
            segundos++;
        }

        if (segundos == 60) {
            segundos = 0;
            minutos++;
        }

        if (minutos == 60) {
            horas++;
            minutos = 0;
        }

        actualizarCronometro();
    };

    public void iniciar() {
        cronometro.start();
    }

    public void pausar() {
        cronometro.stop();
    }

    public void reiniciar() {
        if (cronometro.isRunning()) {
            cronometro.stop();
        }
        horas = 0;
        minutos = 0;
        segundos = 0;
        milisegundos = 0;
        actualizarCronometro();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void actualizarCronometro() {
        String tiempo = (horas <= 9 ? "0" : "") + horas + ":" + (minutos <= 9 ? "0" : "") + minutos + ":" + (segundos <= 9 ? "0" : "") + segundos + ":" + (milisegundos <= 9 ? "0" : "") + milisegundos;
        cronometraje.setText(tiempo);
    }

    public void convierteTiempoACronometro(String tiempo) {
        String[] listaTiempo = tiempo.split(":");
        horas = Integer.parseInt(listaTiempo[0]);
        minutos = Integer.parseInt(listaTiempo[1]);
        segundos = Integer.parseInt(listaTiempo[2]);
        milisegundos = Integer.parseInt(listaTiempo[3]);
        cronometraje.setText(tiempo);
    }

    public String getCronometraje() {
        return cronometraje.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cronometraje = new javax.swing.JLabel();

        setOpaque(false);

        cronometraje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cronometraje.setForeground(new java.awt.Color(255, 255, 255));
        cronometraje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cronometraje.setText("00:00:00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cronometraje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cronometraje, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cronometraje;
    // End of variables declaration//GEN-END:variables
}
