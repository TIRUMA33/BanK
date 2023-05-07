package es.uma.taw.bank.dto;
//Autores Óscar Fernández 50% Alejandro Guerra 50%
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class PersonaDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private String dni;
    private List<Integer> empresaPersonas;
    private List<Integer> empresaClientes;
}