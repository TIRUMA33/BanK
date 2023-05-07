package es.uma.taw.bank.ui;

import es.uma.taw.bank.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroEmpresa {
    private ClienteDTO cliente;
    private DireccionDTO direccion;
    private EmpresaDTO empresa;
    private EmpresaPersonaDTO empresaPersona;
    private PersonaDTO persona;
    private UsuarioDTO usuario;
    private Boolean valida;
    private String fechaNacimiento;
    private String rcontrasena;

    public RegistroEmpresa() {
        cliente = new ClienteDTO();
        direccion = new DireccionDTO();
        empresa = new EmpresaDTO();
        usuario = new UsuarioDTO();
        valida = false;
        rcontrasena = "";
    }
}
