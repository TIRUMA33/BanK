package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "taw", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "CONTENIDO", nullable = false, length = 500)
    private String contenido;
    @Basic
    @Column(name = "FECHA", nullable = false)
    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "CONVERSACION", referencedColumnName = "ID", nullable = false)
    private ConversacionEntity conversacionByConversacion;
    @ManyToOne
    @JoinColumn(name = "EMISOR", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByEmisor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(contenido, that.contenido) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenido, fecha);
    }

    public ConversacionEntity getConversacionByConversacion() {
        return conversacionByConversacion;
    }

    public void setConversacionByConversacion(ConversacionEntity conversacionByConversacion) {
        this.conversacionByConversacion = conversacionByConversacion;
    }

    public UsuarioEntity getUsuarioByEmisor() {
        return usuarioByEmisor;
    }

    public void setUsuarioByEmisor(UsuarioEntity usuarioByEmisor) {
        this.usuarioByEmisor = usuarioByEmisor;
    }
}
