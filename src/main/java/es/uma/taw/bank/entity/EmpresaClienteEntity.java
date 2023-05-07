package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.DTO;
import es.uma.taw.bank.dto.EmpresaClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPRESA_CLIENTE", schema = "taw", catalog = "")
public class EmpresaClienteEntity implements DTO<EmpresaClienteDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID", nullable = false)
    private TipoClienteRelacionadoEntity tipoClienteRelacionadoByIdTipo;
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

        EmpresaClienteEntity that = (EmpresaClienteEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public EmpresaClienteDTO toDTO() {
        EmpresaClienteEntity dto = new EmpresaClienteEntity();

        dto.setId(this.id);

        return null;
    }
}
