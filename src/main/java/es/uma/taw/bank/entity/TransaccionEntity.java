package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "TRANSACCION", schema = "taw", catalog = "")
public class TransaccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "FECHA_INSTRUCCION", nullable = false)
    private Timestamp fechaInstruccion;
    @Basic
    @Column(name = "FECHA_EJECUCION", nullable = false)
    private Timestamp fechaEjecucion;
    @Basic
    @Column(name = "CANTIDAD", nullable = false, precision = 0)
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "CUENTA_ORIGEN", referencedColumnName = "ID", nullable = false)
    private CuentaBancoEntity cuentaBancoByCuentaOrigen;
    @ManyToOne
    @JoinColumn(name = "CUENTA_DESTINO", referencedColumnName = "ID", nullable = false)
    private CuentaBancoEntity cuentaBancoByCuentaDestino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Timestamp fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Timestamp getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Timestamp fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionEntity that = (TransaccionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion, cantidad);
    }

    public CuentaBancoEntity getCuentaBancoByCuentaOrigen() {
        return cuentaBancoByCuentaOrigen;
    }

    public void setCuentaBancoByCuentaOrigen(CuentaBancoEntity cuentaBancoByCuentaOrigen) {
        this.cuentaBancoByCuentaOrigen = cuentaBancoByCuentaOrigen;
    }

    public CuentaBancoEntity getCuentaBancoByCuentaDestino() {
        return cuentaBancoByCuentaDestino;
    }

    public void setCuentaBancoByCuentaDestino(CuentaBancoEntity cuentaBancoByCuentaDestino) {
        this.cuentaBancoByCuentaDestino = cuentaBancoByCuentaDestino;
    }
}
