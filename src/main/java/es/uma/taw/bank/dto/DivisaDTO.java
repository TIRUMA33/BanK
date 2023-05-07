package es.uma.taw.bank.dto;
//Autores Óscar Fernández 50% Alejandro Guerra 50%
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DivisaDTO implements Serializable {
    private Integer id;
    private String nombre;
    private Double equivalencia;
    private List<CuentaDTO> cuentasBancos;
}