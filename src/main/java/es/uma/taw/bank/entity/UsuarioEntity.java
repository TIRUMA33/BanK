package es.uma.taw.bank.entity;

import es.uma.taw.bank.dto.DTO;
import es.uma.taw.bank.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USUARIO", schema = "taw", catalog = "")
public class UsuarioEntity implements DTO<UsuarioDTO> {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NIF", nullable = false, length = 9)
    private String nif;
    @Basic
    @Column(name = "CONTRASENA", nullable = false, length = 45)
    private String contrasena;
    @OneToMany(mappedBy = "usuarioByEmisor")
    private List<ConversacionEntity> conversacionsById;
    @OneToMany(mappedBy = "usuarioByReceptor")
    private List<ConversacionEntity> conversacionsById_0;
    @OneToMany(mappedBy = "usuarioByEmisor")
    private List<MensajeEntity> mensajesById;
    @ManyToOne
    @JoinColumn(name = "TIPO_USUARIO", referencedColumnName = "ID", nullable = false)
    private TipoUsuarioEntity tipoUsuarioByTipoUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        return result;
    }

    @Override
    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(this.id);
        dto.setNif(this.nif);
        dto.setContrasena(this.contrasena);
        dto.setTipoUsuario(this.tipoUsuarioByTipoUsuario.getId());

        return dto;
    }
}
