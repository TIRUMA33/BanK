package es.uma.taw.bank.dto;

/**
 * @author Óscar Fernández Díaz 50%
 * @author Pablo Robles Mansilla 50%
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EstadoCuentaDTO implements Serializable {
    private Integer id;
    private String tipo;
    private List<CuentaDTO> cuentasBanco;
}