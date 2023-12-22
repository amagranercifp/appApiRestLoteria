package com.example.appapirestloteria;

public class Decimo {

    String numero;
    Boolean premiado;
    String cantidad;

    public Decimo(String numero, Boolean premiado, String cantidad) {
        this.numero = numero;
        this.premiado = premiado;
        this.cantidad = cantidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getPremiado() {
        return premiado;
    }

    public void setPremiado(Boolean premiado) {
        this.premiado = premiado;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
