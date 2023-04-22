package es.uma.taw.bank.ui;

import es.uma.taw.bank.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroEmpresaPersona {
    private ClienteEntity cliente;
    private DireccionEntity direccion;
    private EmpresaPersonaEntity empresaPersona;
    private PersonaEntity persona;
    private UsuarioEntity usuario;
    private Boolean valida;
    private String fechaNacimiento;
    private String rcontrasena;

    public RegistroEmpresaPersona() {
        cliente = new ClienteEntity();
        direccion = new DireccionEntity();
        empresaPersona = new EmpresaPersonaEntity();
        persona = new PersonaEntity();
        usuario = new UsuarioEntity();
        valida = false;
        fechaNacimiento = "";
        rcontrasena = "";
    }
}
