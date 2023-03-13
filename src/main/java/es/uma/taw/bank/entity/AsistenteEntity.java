package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "asistente", schema = "taw", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Mensaje", nullable = false, length = 500)
    private String mensaje;
    @Basic
    @Column(name = "Fecha", nullable = false)
    private Timestamp fecha;
    @OneToMany(mappedBy = "asistenteByNumeroIdentificacion")
    private List<ClienteEntity> cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsistenteEntity that = (AsistenteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(mensaje, that.mensaje) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensaje, fecha);
    }

    public List<ClienteEntity> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteEntity> cliente) {
        this.cliente = cliente;
    }
}
