package es.uma.taw.bank.dto;

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
}