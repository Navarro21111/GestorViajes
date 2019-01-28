package com.example.a21736256.gestorviajes.javabean;

public class Viaje {
    private String nombre;
    private String ciudad;
    private String pais;
    private int numDias;
    private int precio;

    public Viaje() {
    }

    public Viaje(String nombre, String ciudad, String pais, int numDias, int precio) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.numDias = numDias;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getNumDias() {
        return numDias;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
