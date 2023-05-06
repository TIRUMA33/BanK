package es.uma.taw.bank.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroEmpresaPersona {

    private String texto;

    private Boolean fechaNacimiento;

    private String tipo;

    public FiltroEmpresaPersona() {
        texto = "";
        fechaNacimiento = false;
        tipo = "";
    }
}
