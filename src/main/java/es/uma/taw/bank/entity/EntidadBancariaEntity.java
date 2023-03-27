package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public List<CuentaBancoEntity> getCuentaBancosById() {
        return cuentaBancosById;
    }

    public void setCuentaBancosById(List<CuentaBancoEntity> cuentaBancosById) {
        this.cuentaBancosById = cuentaBancosById;
    }
}
