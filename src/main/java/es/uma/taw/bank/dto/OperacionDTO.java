package es.uma.taw.bank.dto;

/**
 * @author Óscar Fernández Díaz
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class OperacionDTO implements Serializable {
    private Integer id;
    private Integer persona;
    private String personaNombre;
    private String personaApellido1;
    private String personaApellido2;
    private Date personaFechaNacimiento;
    private String personaDni;
    private Integer transaccion;
    private Timestamp transaccionFechaInstruccion;
    private Timestamp transaccionFechaEjecucion;
    private Double transaccionCantidad;
}