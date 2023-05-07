package es.uma.taw.bank.ui;

import es.uma.taw.bank.dto.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroEmpresaPersona {
    private ClienteDTO cliente;
    private DireccionDTO direccion;
    private EmpresaClienteDTO empresaCliente;
    private EmpresaPersonaDTO empresaPersona;
    private PersonaDTO persona;
    private UsuarioDTO usuario;
    private Boolean valida;
    private String rcontrasena;

    public RegistroEmpresaPersona() {
        cliente = new ClienteDTO();
        direccion = new DireccionDTO();
        empresaCliente = new EmpresaClienteDTO();
        empresaPersona = new EmpresaPersonaDTO();
        persona = new PersonaDTO();
        usuario = new UsuarioDTO();
        valida = false;
        rcontrasena = "";
    }
}
