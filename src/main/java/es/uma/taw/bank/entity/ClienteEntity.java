package es.uma.taw.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE", schema = "taw", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "FECHA_INICIO", nullable = false)
    private Timestamp fechaInicio;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private EstadoClienteEntity estadoClienteByEstadoClienteId;
    @OneToMany(mappedBy = "clienteByTitularId")
    private List<CuentaBancoEntity> cuentaBancosById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private List<DireccionEntity> direccionsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        return result;
    }
}
