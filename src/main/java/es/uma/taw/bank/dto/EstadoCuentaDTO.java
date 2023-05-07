package es.uma.taw.bank.dto;
//Autor Óscar Fernández
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