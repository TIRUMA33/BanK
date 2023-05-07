package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.DTO;
import es.uma.taw.bank.dto.TransaccionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TRANSACCION", schema = "taw", catalog = "")
public class TransaccionEntity implements DTO<TransaccionDTO> {
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

    public TransaccionDTO toDTO(){
        TransaccionDTO dto = new TransaccionDTO();
        dto.setCantidad(this.cantidad);
        dto.setId(this.id);
        dto.setFechaEjecucion(this.fechaEjecucion);
        dto.setFechaInstruccion(this.fechaInstruccion);
        dto.setCuentaOrigen(this.getCuentaBancoByCuentaOrigen().getId());
        dto.setCuentaDestino(this.getCuentaBancoByCuentaDestino().getId());
        dto.setCuentaOrigenIbanCuenta(this.cuentaBancoByCuentaOrigen.getIbanCuenta());
        dto.setCuentaDestinoIbanCuenta(this.cuentaBancoByCuentaDestino.getIbanCuenta());
        dto.setDivisa(this.getCuentaBancoByCuentaOrigen().getDivisaByDivisaId().getNombre());
        dto.setDivisaDestino(this.getCuentaBancoByCuentaDestino().getDivisaByDivisaId().getNombre());

        return dto;
    }
}
