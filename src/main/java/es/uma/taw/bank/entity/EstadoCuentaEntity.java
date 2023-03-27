package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ESTADO_CUENTA", schema = "taw", catalog = "")
public class EstadoCuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "estadoCuentaByEstadoCuentaId")
    private List<CuentaBancoEntity> cuentaBancosById;

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
        EstadoCuentaEntity that = (EstadoCuentaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    public List<CuentaBancoEntity> getCuentaBancosById() {
        return cuentaBancosById;
    }

    public void setCuentaBancosById(List<CuentaBancoEntity> cuentaBancosById) {
        this.cuentaBancosById = cuentaBancosById;
    }
}
