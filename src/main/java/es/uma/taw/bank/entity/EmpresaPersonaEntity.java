package es.uma.taw.bank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPRESA_PERSONA", schema = "taw", catalog = "")
public class EmpresaPersonaEntity {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public TipoPersonaRelacionadaEntity getTipoPersonaRelacionadaByIdTipo() {
        return tipoPersonaRelacionadaByIdTipo;
    }

    public void setTipoPersonaRelacionadaByIdTipo(TipoPersonaRelacionadaEntity tipoPersonaRelacionadaByIdTipo) {
        this.tipoPersonaRelacionadaByIdTipo = tipoPersonaRelacionadaByIdTipo;
    }

    public EmpresaEntity getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    public PersonaEntity getPersonaByIdPersona() {
        return personaByIdPersona;
    }

    public void setPersonaByIdPersona(PersonaEntity personaByIdPersona) {
        this.personaByIdPersona = personaByIdPersona;
    }
}
