package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipodireccion", schema = "taw", catalog = "")
public class TipodireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Tipo", nullable = true, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "tipodireccionByTipoDireccion")
    private List<DireccionEntity> direccionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipodireccionEntity that = (TipodireccionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public List<DireccionEntity> getDireccionsById() {
        return direccionsById;
    }

    public void setDireccionsById(List<DireccionEntity> direccionsById) {
        this.direccionsById = direccionsById;
    }
}
