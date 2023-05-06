package es.uma.taw.bank.ui;

public class FiltroOperacionesPersona {

    private String Iban;
    private Boolean cantidad;

    private Boolean fecha;

    public FiltroOperacionesPersona() {
        Iban = "";
        cantidad = false;
        fecha = false;
    }

    public String getIban() {
        return Iban;
    }

    public void setIban(String iban) {
        Iban = iban;
    }

    public Boolean getCantidad() {
        return cantidad;
    }

    public void setCantidad(Boolean cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getFecha() {
        return fecha;
    }

    public void setFecha(Boolean fecha) {
        this.fecha = fecha;
    }
}
