package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EmpresaDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String cif;
    private List<EmpresaPersonaDTO> empresaPersonasById;
    private List<EmpresaClienteDTO> empresaClientesById;
}