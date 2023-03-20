package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cuentabanco", schema = "taw", catalog = "")
public class CuentabancoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Moneda", nullable = false, length = 10)
    private String moneda;
    @Basic
    @Column(name = "IbanCuenta", nullable = false, length = 24)
    private String ibanCuenta;
    @Basic
    @Column(name = "Saldo", nullable = false, precision = 0)
    private Double saldo;
    @Basic
    @Column(name = "Swift", nullable = false, length = 11)
    private String swift;
    @Basic
    @Column(name = "Pais", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "FechaApertura", nullable = false)
    private Timestamp fechaApertura;
    @Basic
    @Column(name = "FechaCierre", nullable = false)
    private Timestamp fechaCierre;
    @ManyToOne
    @JoinColumn(name = "Titular", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByTitular;
    @ManyToOne
    @JoinColumn(name = "EntidadBancaria", referencedColumnName = "ID", nullable = false)
    private EntidadbancariaEntity entidadbancariaByEntidadBancaria;
    @ManyToOne
    @JoinColumn(name = "EstadoCuenta", referencedColumnName = "ID", nullable = false)
    private EstadocuentaEntity estadocuentaByEstadoCuenta;

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
        CuentabancoEntity that = (CuentabancoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(ibanCuenta, that.ibanCuenta) && Objects.equals(saldo, that.saldo) && Objects.equals(swift, that.swift) && Objects.equals(pais, that.pais) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(fechaCierre, that.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, ibanCuenta, saldo, swift, pais, fechaApertura, fechaCierre);
    }

    public ClienteEntity getClienteByTitular() {
        return clienteByTitular;
    }

    public void setClienteByTitular(ClienteEntity clienteByTitular) {
        this.clienteByTitular = clienteByTitular;
    }

    public EntidadbancariaEntity getEntidadbancariaByEntidadBancaria() {
        return entidadbancariaByEntidadBancaria;
    }

    public void setEntidadbancariaByEntidadBancaria(EntidadbancariaEntity entidadbancariaByEntidadBancaria) {
        this.entidadbancariaByEntidadBancaria = entidadbancariaByEntidadBancaria;
    }

    public EstadocuentaEntity getEstadocuentaByEstadoCuenta() {
        return estadocuentaByEstadoCuenta;
    }

    public void setEstadocuentaByEstadoCuenta(EstadocuentaEntity estadocuentaByEstadoCuenta) {
        this.estadocuentaByEstadoCuenta = estadocuentaByEstadoCuenta;
    }
}
