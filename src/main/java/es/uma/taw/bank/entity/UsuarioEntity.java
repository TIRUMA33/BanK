package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USUARIO", schema = "taw", catalog = "")
public class UsuarioEntity {
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
    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteById;
    @ManyToOne
    @JoinColumn(name = "TIPO_USUARIO", referencedColumnName = "ID", nullable = false)
    private TipoUsuarioEntity tipoUsuarioByTipoUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

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

    public List<ConversacionEntity> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(List<ConversacionEntity> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public List<ConversacionEntity> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(List<ConversacionEntity> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public TipoUsuarioEntity getTipoUsuarioByTipoUsuario() {
        return tipoUsuarioByTipoUsuario;
    }

    public void setTipoUsuarioByTipoUsuario(TipoUsuarioEntity tipoUsuarioByTipoUsuario) {
        this.tipoUsuarioByTipoUsuario = tipoUsuarioByTipoUsuario;
    }
}
