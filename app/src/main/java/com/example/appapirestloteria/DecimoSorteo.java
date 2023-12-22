package com.example.appapirestloteria;

import java.util.Objects;

public class DecimoSorteo {

    String numero;
    String premio;

    public DecimoSorteo(String numero, String premio) {
        this.numero = numero;
        this.premio = premio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast del objeto a DecimoSorteo
        DecimoSorteo otroSorteo = (DecimoSorteo) obj;

        // Comparación basada en el datoString
        return Objects.equals(numero, otroSorteo.numero);
    }
}
