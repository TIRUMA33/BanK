package es.uma.taw.bank.ui;

public class FiltroOperacionesPersona {

    private Boolean fechaEjecucion;

    private double cantidad;

    public FiltroOperacionesPersona() {
        fechaEjecucion = false;
        cantidad = 0.0;
    }

    public Boolean getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Boolean fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
