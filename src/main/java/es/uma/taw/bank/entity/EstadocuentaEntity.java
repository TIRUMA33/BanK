package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estadocuenta", schema = "taw", catalog = "")
public class EstadocuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Tipo", nullable = false, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "estadocuentaByEstadoCuenta")
    private List<CuentabancoEntity> cuentabancosById;

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
        EstadocuentaEntity that = (EstadocuentaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public List<CuentabancoEntity> getCuentabancosById() {
        return cuentabancosById;
    }

    public void setCuentabancosById(List<CuentabancoEntity> cuentabancosById) {
        this.cuentabancosById = cuentabancosById;
    }
}
