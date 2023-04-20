package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ENTIDAD_BANCARIA", schema = "taw", catalog = "")
public class EntidadBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "entidadBancariaByEntidadBancariaId")
    private List<CuentaBancoEntity> cuentaBancosById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadBancariaEntity that = (EntidadBancariaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<CuentaBancoEntity> getCuentaBancosById() {
        return cuentaBancosById;
    }

    public void setCuentaBancosById(List<CuentaBancoEntity> cuentaBancosById) {
        this.cuentaBancosById = cuentaBancosById;
    }
}
