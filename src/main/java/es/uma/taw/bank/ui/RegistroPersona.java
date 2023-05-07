package es.uma.taw.bank.ui;
//@author: Pablo Ruiz Galianez

import es.uma.taw.bank.entity.ClienteEntity;
import es.uma.taw.bank.entity.DireccionEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import es.uma.taw.bank.entity.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroPersona {

    private ClienteEntity cliente;
    private DireccionEntity direccion;
    private PersonaEntity persona;
    private UsuarioEntity usuario;
    private Boolean valida;
    private String rcontrasena;

    public RegistroPersona() {
        cliente = new ClienteEntity();
        direccion = new DireccionEntity();
        usuario = new UsuarioEntity();
        valida = false;
        rcontrasena = "";
    }
}
