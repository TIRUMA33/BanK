package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MENSAJE", schema = "taw", catalog = "")
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
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
