package es.uma.taw.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ConversacionDTO implements Serializable {
    private Integer id;
    private Byte terminada;
}