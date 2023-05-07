package es.uma.taw.bank.ui;

/**
 * @author Óscar Fernández Díaz
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroOperacionesEmpresa {

    private String cuenta;

    private Boolean cantidad;

    private Boolean fechaEjecucion;

    public FiltroOperacionesEmpresa() {
        cuenta = "";
        cantidad = false;
        fechaEjecucion = false;
    }
}
