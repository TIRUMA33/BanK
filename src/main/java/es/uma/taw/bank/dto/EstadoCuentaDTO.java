package es.uma.taw.bank.dto;

import es.uma.taw.bank.entity.CuentaBancoEntity;

import java.io.Serializable;
import java.util.List;

public class EstadoCuentaDTO implements Serializable {

    private Integer id;

    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
