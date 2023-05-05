package es.uma.taw.bank.ui;

public class FiltroCliente {

    private Boolean nombre;
    private Boolean estado;

    public FiltroCliente(){
        nombre = false;
        estado = false;
    }

    public Boolean getNombre() {
        return nombre;
    }

    public void setNombre(Boolean nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
