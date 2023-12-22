package com.example.appapirestloteria;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast del objeto a Decimo
        Decimo otroSorteo = (Decimo) obj;

        // Comparación basada en el datoString
        return Objects.equals(numero, otroSorteo.numero);
    }
}
