package es.uma.taw.bank.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link es.uma.taw.bank.entity.TipoUsuarioEntity} entity
 */
@Data
public class TipoUsuarioDTO implements Serializable {
    private final Integer id;
    private final String tipo;
}