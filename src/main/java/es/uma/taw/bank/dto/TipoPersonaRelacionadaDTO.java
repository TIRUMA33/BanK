package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TipoPersonaRelacionadaDTO implements Serializable {
    private Integer id;
    private String tipo;
    private List<Integer> empresaPersonas;
}