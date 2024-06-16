package com.tcna.primeraweb.progra_4.logic;

public class Categoria {
    String id;
    String nombre;

    public Categoria() {
    }

    public Categoria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
