package com.tcna.primeraweb.progra_4.logic;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Factura", schema = "proyecto_1")

@Data
public class FacturaEntity {

    @Id
    int  factura_id ;
    Date fecha ;
    float  total ;
    String cliente ;



    public FacturaEntity() {
    }

    public FacturaEntity(int factura_id, Date fecha, float total, String cliente) {
        this.factura_id = factura_id;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

    public int getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(int factura_id) {
        this.factura_id = factura_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
