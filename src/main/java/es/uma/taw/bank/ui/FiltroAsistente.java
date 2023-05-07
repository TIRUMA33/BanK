package es.uma.taw.bank.ui;
//Autor Pablo Robles Mansilla

public class FiltroAsistente {
    private boolean pendientes;
    private Byte estado;
    private String dni;
    private boolean fecha;


    public FiltroAsistente() {
        pendientes = false;
        estado = null;
        dni = "";
        fecha = false;

    }

    public boolean isPendientes() {
        return pendientes;
    }

    public void setPendientes(boolean pendientes) {
        this.pendientes = pendientes;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isFecha() {
        return fecha;
    }

    public void setFecha(boolean fecha) {
        this.fecha = fecha;
    }
}
