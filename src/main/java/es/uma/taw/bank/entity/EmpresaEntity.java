package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "EMPRESA", schema = "taw", catalog = "")
public class EmpresaEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "CIF", nullable = false, length = 9)
    private String cif;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<EmpresaPersonaEntity> empresaPersonasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpresaEntity that = (EmpresaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (cif != null ? !cif.equals(that.cif) : that.cif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (cif != null ? cif.hashCode() : 0);
        return result;
    }

    public List<EmpresaPersonaEntity> getEmpresaPersonasById() {
        return empresaPersonasById;
    }

    public void setEmpresaPersonasById(List<EmpresaPersonaEntity> empresaPersonasById) {
        this.empresaPersonasById = empresaPersonasById;
    }
}
