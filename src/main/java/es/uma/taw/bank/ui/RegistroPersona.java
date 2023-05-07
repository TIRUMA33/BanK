package es.uma.taw.bank.ui;

import es.uma.taw.bank.dto.ClienteDTO;
import es.uma.taw.bank.dto.DireccionDTO;
import es.uma.taw.bank.dto.PersonaDTO;
import es.uma.taw.bank.DTO.*;
import es.uma.taw.bank.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
public class RegistroPersona {

    private ClienteDTO cliente;
    private DireccionDTO direccion;
    private PersonaDTO persona;
    private UsuarioDTO usuario;
    private Boolean valida;
    private String rcontrasena;

    public RegistroPersona(){
        cliente = new ClienteDTO();
        direccion = new DireccionDTO();
        usuario = new UsuarioDTO();
        valida = false;
        rcontrasena = "";
    }
}
