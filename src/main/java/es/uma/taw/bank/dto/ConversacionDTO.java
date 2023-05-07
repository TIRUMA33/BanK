package es.uma.taw.bank.dto;

/**
 * @author Óscar Fernández Díaz
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ConversacionDTO implements Serializable {
    private Integer id;
    private Byte terminada;
    private Timestamp fechaCreacion;
    private Integer emisor;
    private String emisorNif;
    private String emisorContrasena;
    private Integer receptor;
    private String receptorNif;
    private String receptorContrasena;
    private List<MensajeDTO> mensajes;
}