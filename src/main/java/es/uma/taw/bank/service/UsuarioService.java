package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.TipoUsuarioRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.dto.UsuarioDTO;
import es.uma.taw.bank.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO buscarUsuario(Integer id) {
        return Objects.requireNonNull(this.usuarioRepository.findById(id).orElse(null)).toDTO();
    }

    public void guardarUsuario(UsuarioDTO dto){
        UsuarioEntity usuario;
        usuario = new UsuarioEntity();
        usuario.setNif(dto.getNif());
        usuario.setContrasena(dto.getContrasena());
        usuario.setId(dto.getId());
        usuario.setTipoUsuarioByTipoUsuario(tipoUsuarioRepository.findById(dto.getTipoUsuario()).orElse(null));
        this.usuarioRepository.save(usuario);
    }

    public void guardarUsuario(UsuarioDTO dto, String nif, String rContrasena) {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNif(nif);
        if (!(dto.getContrasena().isBlank() || rContrasena.isBlank())) {
            if (dto.getContrasena().equals(rContrasena)) {
                usuario.setContrasena(dto.getContrasena());
            }
        }

        this.usuarioRepository.save(usuario);
    }

    public UsuarioDTO autenticar(String nif, String contrasena) {
        UsuarioEntity usuario = this.usuarioRepository.autenticar(nif, contrasena);
        if (usuario != null) {
            return usuario.toDTO();
        } else {
            return null;
        }
    }
}
