package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

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
}