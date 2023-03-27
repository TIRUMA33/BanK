package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaInicio, that.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio);
    }

    public EstadoClienteEntity getEstadoClienteByEstadoClienteId() {
        return estadoClienteByEstadoClienteId;
    }

    public void setEstadoClienteByEstadoClienteId(EstadoClienteEntity estadoClienteByEstadoClienteId) {
        this.estadoClienteByEstadoClienteId = estadoClienteByEstadoClienteId;
    }
}
