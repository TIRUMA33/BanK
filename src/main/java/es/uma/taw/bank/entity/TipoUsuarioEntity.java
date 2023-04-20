package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TIPO_USUARIO", schema = "taw", catalog = "")
public class TipoUsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TIPO", nullable = true, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "tipoUsuarioByTipoUsuario")
    private List<UsuarioEntity> usuariosById;

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
        TipoUsuarioEntity that = (TipoUsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public List<UsuarioEntity> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(List<UsuarioEntity> usuariosById) {
        this.usuariosById = usuariosById;
    }
}