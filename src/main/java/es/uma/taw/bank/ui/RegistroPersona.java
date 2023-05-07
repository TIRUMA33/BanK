package es.uma.taw.bank.ui;
//@author: Pablo Ruiz Galianez
import es.uma.taw.bank.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
public class RegistroPersona {

    private ClienteEntity cliente;
    private DireccionEntity direccion;
    private PersonaEntity persona;
    private UsuarioEntity usuario;
    private Boolean valida;
    private String rcontrasena;

    public RegistroPersona(){
        cliente = new ClienteEntity();
        direccion = new DireccionEntity();
        usuario = new UsuarioEntity();
        valida = false;
        rcontrasena = "";
    }
}
