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
public class TipoClienteRelacionadoDTO implements Serializable {
    private Integer id;
    private String tipo;
    private List<Integer> empresaClientes;
}