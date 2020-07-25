package com.Modelo;

import java.util.Objects;

public class Cliente extends ModeloGeneral implements Comparable<Cliente> {

    private Long id;
    private String nombre;
    private int estado;
    private String telefono;
    private int tipoCliente;
    private int id_sistema;
    private String contacto;

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, int tipoCliente, int id_sistema, String contacto) {
        this.nombre = nombre;
        this.estado = Parametros.ACTIVO.getId();
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.id_sistema = id_sistema;
        this.contacto = contacto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int gettCliente() {
        return tipoCliente;
    }

    public void settCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getId_sistema() {
        return id_sistema;
    }

    public void setId_sistema(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }

    @Override
    public int compareTo(Cliente o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }

    @Override
    public Long getIdElement() {
        return this.id;
    }

}
