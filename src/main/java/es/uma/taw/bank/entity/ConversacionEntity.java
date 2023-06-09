package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.ConversacionDTO;
import es.uma.taw.bank.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CONVERSACION", schema = "taw", catalog = "")
public class ConversacionEntity implements DTO<ConversacionDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TERMINADA", nullable = true, columnDefinition = "byte default 0")
    private Byte terminada;
    @Basic
    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "EMISOR", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByEmisor;
    @ManyToOne
    @JoinColumn(name = "RECEPTOR", referencedColumnName = "ID", nullable = false, columnDefinition = "integer " +
            "default" + " 27")
    private UsuarioEntity usuarioByReceptor;
    @OneToMany(mappedBy = "conversacionByConversacion")
    private List<MensajeEntity> mensajesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversacionEntity that = (ConversacionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (terminada != null ? !terminada.equals(that.terminada) : that.terminada != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (terminada != null ? terminada.hashCode() : 0);
        return result;
    }

    @Override
    public ConversacionDTO toDTO() {
        ConversacionDTO dto = new ConversacionDTO();
        dto.setId(this.id);
        dto.setTerminada(this.terminada);
        dto.setFechaCreacion(this.fechaCreacion);
        dto.setEmisor(this.usuarioByEmisor.getId());
        dto.setEmisorContrasena(this.usuarioByEmisor.getContrasena());
        dto.setEmisorNif(this.usuarioByEmisor.getNif());
        dto.setReceptor(this.usuarioByReceptor.getId());
        dto.setReceptorContrasena(this.usuarioByReceptor.getContrasena());
        dto.setReceptorNif(this.usuarioByReceptor.getNif());
        return dto;
    }
}
