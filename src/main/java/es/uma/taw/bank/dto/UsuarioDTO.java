package es.uma.taw.bank.dto;
//Autor Óscar Fernández
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private Integer id;
    private String nif;
    private String contrasena;
    private List<ConversacionDTO> conversacionesEmisor;
    private List<ConversacionDTO> conversacionesReceptor;
    private List<MensajeDTO> mensajes;
    private Integer tipoUsuario;
    private String tipoUsuarioTipo;
}