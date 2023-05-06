package es.uma.taw.bank.dto;

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
    private List<ConversacionDTO> conversacionsById;
    private List<ConversacionDTO> conversacionsById_0;
    private List<MensajeDTO> mensajesById;
    private TipoUsuarioDTO tipoUsuarioByTipoUsuario;
}