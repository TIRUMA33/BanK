package es.uma.taw.bank.ui;

import es.uma.taw.bank.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class  RegistroEmpresa {
    private ClienteEntity cliente;
    private DireccionEntity direccion;
    private EmpresaEntity empresa;
    private EmpresaPersonaEntity empresaPersona;
    private PersonaEntity persona;
    private UsuarioEntity usuario;
    private Boolean valida;
    private String fechaNacimiento;
    private String rcontrasena;

    public RegistroEmpresa() {
        cliente = new ClienteEntity();
        direccion = new DireccionEntity();
        empresa = new EmpresaEntity();
        usuario = new UsuarioEntity();
        valida = false;
        rcontrasena = "";
    }
}
