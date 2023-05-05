package es.uma.taw.bank.dto;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class TransaccionDTO implements Serializable {
    private Integer id;
    private Timestamp fechaInstruccion;
    private Timestamp fechaEjecucion;
    private Double cantidad;
    private Integer cuentaOrigen;
    private Integer cuentaDestino;
    private String ibanOrigen;
    private String ibanDestino;

    public String getIbanOrigen() {
        return ibanOrigen;
    }

    public void setIbanOrigen(String ibanOrigen) {
        this.ibanOrigen = ibanOrigen;
    }

    public String getIbanDestino() {
        return ibanDestino;
    }

    public void setIbanDestino(String ibanDestino) {
        this.ibanDestino = ibanDestino;
    }

    public Integer getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Integer cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Integer getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Integer cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Timestamp fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Timestamp getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Timestamp fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
