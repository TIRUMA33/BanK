package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DireccionDTO implements Serializable {
    private Integer id;
    private String calle;
    private Integer numero;
    private String plantaPuertaOficina;
    private String ciudad;
    private String region;
    private Integer codigoPostal;
    private String pais;
    private Byte valida;
    private ClienteDTO clienteByClienteId;
}