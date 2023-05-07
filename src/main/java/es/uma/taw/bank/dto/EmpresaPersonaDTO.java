package es.uma.taw.bank.dto;
//Autor Óscar Fernández
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class EmpresaPersonaDTO implements Serializable {
    private Integer id;
    private Integer tipoPersonaRelacionada;
    private String tipoPersonaRelacionadaTipo;
    private Integer empresa;
    private String empresaNombre;
    private String empresaCif;
    private Integer persona;
    private String personaNombre;
    private String personaApellido1;
    private String personaApellido2;
    private Date personaFechaNacimiento;
    private String personaDni;
}