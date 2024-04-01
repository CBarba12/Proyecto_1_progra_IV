package com.tcna.primeraweb.progra_4.logic;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Perfil_Hacienda", schema = "proyecto_1")

@Data
public class PerfilHaciendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int perfil_hacienda_id ;
    String Nombre ;
    String tipo_contribuyente ;
    String numero_identificacion ;
    String direccion ;
    int telefono ;
    String provedor_idetificacion;

    public PerfilHaciendaEntity(int perfil_hacienda_id, String nombre, String tipo_contribuyente, String numero_identificacion, String direccion, int telefono, String provedor_idetificacion) {
        this.perfil_hacienda_id = perfil_hacienda_id;
        Nombre = nombre;
        this.tipo_contribuyente = tipo_contribuyente;
        this.numero_identificacion = numero_identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.provedor_idetificacion = provedor_idetificacion;
    }


    public PerfilHaciendaEntity() {
    }

    public int getPerfil_hacienda_id() {
        return perfil_hacienda_id;
    }

    public void setPerfil_hacienda_id(int perfil_hacienda_id) {
        this.perfil_hacienda_id = perfil_hacienda_id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo_contribuyente() {
        return tipo_contribuyente;
    }

    public void setTipo_contribuyente(String tipo_contribuyente) {
        this.tipo_contribuyente = tipo_contribuyente;
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getProvedor_idetificacion() {
        return provedor_idetificacion;
    }

    public void setProvedor_idetificacion(String provedor_idetificacion) {
        this.provedor_idetificacion = provedor_idetificacion;
    }
}
