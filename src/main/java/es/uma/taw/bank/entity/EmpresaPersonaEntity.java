package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.DTO;
import es.uma.taw.bank.dto.EmpresaPersonaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPRESA_PERSONA", schema = "taw", catalog = "")
public class EmpresaPersonaEntity implements DTO<EmpresaPersonaDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID", nullable = false)
    private TipoPersonaRelacionadaEntity tipoPersonaRelacionadaByIdTipo;
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    private EmpresaEntity empresaByIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByIdPersona;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpresaPersonaEntity that = (EmpresaPersonaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public EmpresaPersonaDTO toDTO() {
        EmpresaPersonaDTO dto = new EmpresaPersonaDTO();

        dto.setId(this.id);

        return dto;
    }
}
