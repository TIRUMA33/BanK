package es.uma.taw.bank.dto;

/**
 * @author Óscar Fernández Díaz
 */

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
    private List<Integer> empresaPersonas;
    private List<Integer> empresaClientes;
}