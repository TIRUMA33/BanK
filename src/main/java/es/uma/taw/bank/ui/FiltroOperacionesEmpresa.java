package es.uma.taw.bank.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroOperacionesEmpresa {

    private String socio;

    private String cuenta;

    private Double cantidad;

    private Boolean fechaEjecucion;

    public FiltroOperacionesEmpresa() {
        socio = "";
        cuenta = "";
        fechaEjecucion = false;
    }
}
