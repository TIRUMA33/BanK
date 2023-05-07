package es.uma.taw.bank.dto;
//Autor Óscar Fernández
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class MensajeDTO implements Serializable {
    private Integer id;
    private String contenido;
    private Timestamp fecha;
    private Integer conversacion;
    private Byte conversacionTerminada;
    private Timestamp conversacionFechaCreacion;
    private Integer emisor;
    private String emisorNif;
    private String emisorContrasena;
}