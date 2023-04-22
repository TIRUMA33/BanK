package es.uma.taw.bank.ui;

import java.util.Date;

public class FiltroOperaciones {
    private Boolean fechaInstruccion;
    private Boolean fechaEjecucion;
    private Boolean cantidad;
    private String cuentaFiltro;

    public FiltroOperaciones() {
        fechaInstruccion=false;
        fechaEjecucion=false;
        cantidad=false;
        cuentaFiltro=null;
    }

    public Boolean getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Boolean fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Boolean getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Boolean fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Boolean getCantidad() {
        return cantidad;
    }

    public void setCantidad(Boolean cantidad) {
        this.cantidad = cantidad;
    }

    public String getCuentaFiltro() {
        return cuentaFiltro;
    }

    public void setCuentaFiltro(String cuentaFiltro) {
        this.cuentaFiltro = cuentaFiltro;
    }
}
