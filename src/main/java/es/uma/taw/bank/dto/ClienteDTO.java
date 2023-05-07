package es.uma.taw.bank.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClienteDTO implements Serializable {

    private Integer id;

    private Timestamp fechaInicio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}

