package es.uma.taw.bank.dto;
//Autores Alejandro Guerra 50% Óscar Fernández 50%
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ClienteDTO implements Serializable {
    private Integer id;
    private Timestamp fechaInicio;
    private Integer estadoCliente;
    private String estadoClienteTipo;
    private List<CuentaDTO> cuentasBanco;
    private List<DireccionDTO> direcciones;
}