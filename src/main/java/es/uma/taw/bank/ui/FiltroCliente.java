package es.uma.taw.bank.ui;
//@author: David Castaños Benedicto
public class FiltroCliente {

    private Boolean nombre;
    private Boolean estado;

    private String texto;

    public FiltroCliente(){
        nombre = false;
        estado = false;
        texto = "";
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
