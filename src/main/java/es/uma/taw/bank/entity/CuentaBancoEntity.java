package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CUENTA_BANCO", schema = "taw", catalog = "")
public class CuentaBancoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MONEDA", nullable = false, length = 10)
    private String moneda;
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
    @Column(name = "FECHA_CIERRE", nullable = false)
    private Timestamp fechaCierre;
    @ManyToOne
    @JoinColumn(name = "TITULAR_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByTitularId;
    @ManyToOne
    @JoinColumn(name = "ENTIDAD_BANCARIA_ID", referencedColumnName = "ID", nullable = false)
    private EntidadBancariaEntity entidadBancariaByEntidadBancariaId;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CUENTA_ID", referencedColumnName = "ID", nullable = false)
    private EstadoCuentaEntity estadoCuentaByEstadoCuentaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getIbanCuenta() {
        return ibanCuenta;
    }

    public void setIbanCuenta(String ibanCuenta) {
        this.ibanCuenta = ibanCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Timestamp getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Timestamp fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancoEntity that = (CuentaBancoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(ibanCuenta, that.ibanCuenta) && Objects.equals(saldo, that.saldo) && Objects.equals(swift, that.swift) && Objects.equals(pais, that.pais) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, ibanCuenta, saldo, swift, pais, fechaApertura, fechaCierre);
    }

    public ClienteEntity getClienteByTitularId() {
        return clienteByTitularId;
    }

    public void setClienteByTitularId(ClienteEntity clienteByTitularId) {
        this.clienteByTitularId = clienteByTitularId;
    }

    public EntidadBancariaEntity getEntidadBancariaByEntidadBancariaId() {
        return entidadBancariaByEntidadBancariaId;
    }

    public void setEntidadBancariaByEntidadBancariaId(EntidadBancariaEntity entidadBancariaByEntidadBancariaId) {
        this.entidadBancariaByEntidadBancariaId = entidadBancariaByEntidadBancariaId;
    }

    public EstadoCuentaEntity getEstadoCuentaByEstadoCuentaId() {
        return estadoCuentaByEstadoCuentaId;
    }

    public void setEstadoCuentaByEstadoCuentaId(EstadoCuentaEntity estadoCuentaByEstadoCuentaId) {
        this.estadoCuentaByEstadoCuentaId = estadoCuentaByEstadoCuentaId;
    }
}
