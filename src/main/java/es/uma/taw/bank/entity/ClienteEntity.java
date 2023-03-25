package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "taw", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Fechainicio", nullable = false)
    private Timestamp fechaInicio;
    @ManyToOne
    @JoinColumn(name = "Estadocliente", referencedColumnName = "ID", nullable = false)
    private EstadoclienteEntity estadoclienteByEstadoCliente;

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

    public EstadoclienteEntity getEstadoclienteByEstadoCliente() {
        return estadoclienteByEstadoCliente;
    }

    public void setEstadoclienteByEstadoCliente(EstadoclienteEntity estadoclienteByEstadoCliente) {
        this.estadoclienteByEstadoCliente = estadoclienteByEstadoCliente;
    }
}
