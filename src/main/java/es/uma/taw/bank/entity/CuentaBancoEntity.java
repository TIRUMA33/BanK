package es.uma.taw.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CUENTA_BANCO", schema = "taw", catalog = "")
public class CuentaBancoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "IBAN_CUENTA", nullable = false, length = 24)
    private String ibanCuenta;
    @Basic
    @Column(name = "SALDO", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "SWIFT", nullable = false, length = 11)
    private String swift;
    @Basic
    @Column(name = "PAIS", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "FECHA_APERTURA", nullable = false)
    private Timestamp fechaApertura;
    @Basic
    @Column(name = "FECHA_CIERRE", nullable = true)
    private Timestamp fechaCierre;
    @ManyToOne
    @JoinColumn(name = "TITULAR_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByTitularId;
    @ManyToOne
    @JoinColumn(name = "DIVISA_ID", referencedColumnName = "ID", nullable = false)
    private DivisaEntity divisaByDivisaId;
    @ManyToOne
    @JoinColumn(name = "ENTIDAD_BANCARIA_ID", referencedColumnName = "ID", nullable = false)
    private EntidadBancariaEntity entidadBancariaByEntidadBancariaId;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CUENTA_ID", referencedColumnName = "ID", nullable = false)
    private EstadoCuentaEntity estadoCuentaByEstadoCuentaId;
    @OneToMany(mappedBy = "cuentaBancoByCuentaOrigen")
    private List<TransaccionEntity> transaccionsById;
    @OneToMany(mappedBy = "cuentaBancoByCuentaDestino")
    private List<TransaccionEntity> transaccionsById_0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaBancoEntity that = (CuentaBancoEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ibanCuenta != null ? !ibanCuenta.equals(that.ibanCuenta) : that.ibanCuenta != null) return false;
        if (saldo != null ? !saldo.equals(that.saldo) : that.saldo != null) return false;
        if (swift != null ? !swift.equals(that.swift) : that.swift != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;
        if (fechaApertura != null ? !fechaApertura.equals(that.fechaApertura) : that.fechaApertura != null)
            return false;
        if (fechaCierre != null ? !fechaCierre.equals(that.fechaCierre) : that.fechaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ibanCuenta != null ? ibanCuenta.hashCode() : 0);
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (swift != null ? swift.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (fechaApertura != null ? fechaApertura.hashCode() : 0);
        result = 31 * result + (fechaCierre != null ? fechaCierre.hashCode() : 0);
        return result;
    }
}
