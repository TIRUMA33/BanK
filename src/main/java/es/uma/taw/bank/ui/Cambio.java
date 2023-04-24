package es.uma.taw.bank.ui;

public class Cambio {
    private String monedaDestino;
    private Double equivalencia;

    public Cambio() {
        this.monedaDestino = "";
        this.equivalencia = null;
    }

    public Cambio(String monedaDestino, Double equivalencia){
        this.monedaDestino=monedaDestino;
        this.equivalencia=equivalencia;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public Double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Double equivalencia) {
        this.equivalencia = equivalencia;
    }
}
