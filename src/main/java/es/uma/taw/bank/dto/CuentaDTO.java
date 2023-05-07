package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CuentaDTO implements Serializable {
    private Integer id;
    private String ibanCuenta;
    private Double saldo;
    private String swift;
    private String pais;
    private Timestamp fechaApertura;
    private Timestamp fechaCierre;
    private Integer cliente;
    private Timestamp clienteFechaInicio;
    private Integer divisa;
    private String divisaNombre;
    private Double divisaEquivalencia;
    private Integer entidad;
    private String entidadNombre;
    private Integer estado;
    private String estadoTipo;
    private List<TransaccionDTO> transaccionesOrigen;
    private List<TransaccionDTO> transaccionesDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaDTO entity = (CuentaDTO) o;
        return Objects.equals(this.id, entity.id);
    }
}