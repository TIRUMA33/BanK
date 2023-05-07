package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.DTO;
import es.uma.taw.bank.dto.TipoPersonaRelacionadaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TIPO_PERSONA_RELACIONADA", schema = "taw", catalog = "")
public class TipoPersonaRelacionadaEntity implements DTO<TipoPersonaRelacionadaDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "tipoPersonaRelacionadaByIdTipo")
    private List<EmpresaPersonaEntity> empresaPersonasById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoPersonaRelacionadaEntity that = (TipoPersonaRelacionadaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    @Override
    public TipoPersonaRelacionadaDTO toDTO() {
        TipoPersonaRelacionadaDTO dto = new TipoPersonaRelacionadaDTO();

        dto.setId(this.id);
        dto.setTipo(this.tipo);

        return dto;
    }
}
