package es.uma.taw.bank.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FiltroEmpresaPersona {

    private String texto;

    private Boolean fechaNacimiento;

    private List<String> tipo;

    public FiltroEmpresaPersona() {
        texto = "";
        fechaNacimiento = false;
        tipo = null;
    }
}
