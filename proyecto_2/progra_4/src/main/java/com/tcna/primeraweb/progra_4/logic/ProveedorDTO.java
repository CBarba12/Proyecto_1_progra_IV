package com.tcna.primeraweb.progra_4.logic;

public class ProveedorDTO {

    private String idProveedor; // PK
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String estado;
    private boolean admin;
    private int telefono;
    private String direccion;
    private String tipoProveedor;
    private String actividadComercial;

    // Constructor
    public ProveedorDTO() {}

    // Getters y Setters

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getActividadComercial() {
        return actividadComercial;
    }

    public void setActividadComercial(String actividadComercial) {
        this.actividadComercial = actividadComercial;
    }
}

