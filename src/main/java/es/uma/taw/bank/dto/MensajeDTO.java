package es.uma.taw.bank.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link es.uma.taw.bank.entity.MensajeEntity} entity
 */
@Data
public class MensajeDTO implements Serializable {
    private final Integer id;
    private final String contenido;
    private final Timestamp fecha;
}