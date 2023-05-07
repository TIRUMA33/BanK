package es.uma.taw.bank.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link es.uma.taw.bank.entity.TipoUsuarioEntity} entity
 */
@Data
public class TipoUsuarioDTO implements Serializable {
    private int id;
    private String tipo;

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}