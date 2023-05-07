package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class TransaccionDTO implements Serializable {
    private Integer id;
    private Timestamp fechaInstruccion;
    private Timestamp fechaEjecucion;
    private Double cantidad;
    private Integer cuentaOrigen;
    private String cuentaOrigenIbanCuenta;
    private Double cuentaOrigenSaldo;
    private String cuentaOrigenSwift;
    private String cuentaOrigenPais;
    private Timestamp cuentaOrigenFechaApertura;
    private Timestamp cuentaOrigenFechaCierre;
    private Integer cuentaDestino;
    private String cuentaDestinoIbanCuenta;
    private Double cuentaDestinoSaldo;
    private String cuentaDestinoSwift;
    private String cuentaDestinoPais;
    private Timestamp cuentaDestinoFechaApertura;
    private Timestamp cuentaDestinoFechaCierre;
}