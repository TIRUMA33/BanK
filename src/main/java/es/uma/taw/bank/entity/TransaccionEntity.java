package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transaccion", schema = "taw", catalog = "")
public class TransaccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "FechaInstruccion", nullable = false)
    private Timestamp fechaInstruccion;
    @Basic
    @Column(name = "FechaEjecucion", nullable = false)
    private Timestamp fechaEjecucion;
    @Basic
    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;
    @OneToMany(mappedBy = "transaccionByIbanCuenta")
    private Collection<CuentabancoEntity> cuentaDestino;
    @OneToMany(mappedBy = "transaccionByIbanCuenta_0")
    private Collection<CuentabancoEntity> cuentaOrigen;

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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
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

    public Collection<CuentabancoEntity> getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Collection<CuentabancoEntity> cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Collection<CuentabancoEntity> getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Collection<CuentabancoEntity> cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }
}
