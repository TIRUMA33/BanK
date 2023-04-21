package es.uma.taw.bank.ui;

import es.uma.taw.bank.entity.ClienteEntity;
import es.uma.taw.bank.entity.DireccionEntity;
import es.uma.taw.bank.entity.EmpresaEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroEmpresa {
    private ClienteEntity cliente;
    private DireccionEntity direccion;
    private EmpresaEntity empresa;
    private UsuarioEntity usuario;
    private Boolean valida;
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
