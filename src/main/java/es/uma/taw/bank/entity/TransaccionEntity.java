package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fechaInstruccion != null ? !fechaInstruccion.equals(that.fechaInstruccion) : that.fechaInstruccion != null)
            return false;
        if (fechaEjecucion != null ? !fechaEjecucion.equals(that.fechaEjecucion) : that.fechaEjecucion != null)
            return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fechaInstruccion != null ? fechaInstruccion.hashCode() : 0);
        result = 31 * result + (fechaEjecucion != null ? fechaEjecucion.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
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
