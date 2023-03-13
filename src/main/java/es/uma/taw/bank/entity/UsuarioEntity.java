package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "taw", catalog = "")
public class UsuarioEntity {
    @Basic
    @Column(name = "Contrasena", nullable = false, length = 45)
    private String contrasena;
    @OneToMany(mappedBy = "usuarioByNumeroIdentificacion")
    private List<ClienteEntity> cliente;

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(contrasena, that.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contrasena);
    }

    public List<ClienteEntity> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteEntity> cliente) {
        this.cliente = cliente;
    }
}
