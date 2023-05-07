package es.uma.taw.bank.service;
//Pablo Ruiz Galianez 10% Óscar Fernández 90%

import es.uma.taw.bank.dao.TipoUsuarioRepository;
import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.dto.UsuarioDTO;
import es.uma.taw.bank.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return usuarioRepository.findById(id)
                .map(UsuarioEntity::toDTO)
                .orElse(null);
    }

    public void guardarUsuario(UsuarioDTO dto, Integer id, String ncif, Integer tipo) {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setId(id);
        usuario.setNif(ncif);
        usuario.setContrasena(dto.getContrasena());
        usuario.setTipoUsuarioByTipoUsuario(tipoUsuarioRepository.findById(tipo).orElse(null));

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

    public void borrarUsuario(Integer id) {
        this.usuarioRepository.deleteById(id);
    }
}
