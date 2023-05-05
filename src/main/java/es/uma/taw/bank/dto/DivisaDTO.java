package es.uma.taw.bank.dto;

import java.io.Serializable;

public class DivisaDTO implements Serializable {
    private Integer id;
    private String nombre;
    private Double equivalencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Double equivalencia) {
        this.equivalencia = equivalencia;
    }
}
