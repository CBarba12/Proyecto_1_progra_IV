package com.tcna.primeraweb.progra_4.logic;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas_productos", schema = "proyecto_1")
public class FacturasProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_producto")
    private int idProducto;
    @Basic
    @Column(name = "id_factura")
    private int idFactura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacturasProductosEntity that = (FacturasProductosEntity) o;

        if (id != that.id) return false;
        if (idProducto != that.idProducto) return false;
        if (idFactura != that.idFactura) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idProducto;
        result = 31 * result + idFactura;
        return result;
    }
}
