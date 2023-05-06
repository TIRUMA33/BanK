package es.uma.taw.bank.service;

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
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void guardarUsuario(UsuarioDTO dto){
        UsuarioEntity usuario;
        usuario = new UsuarioEntity();
        usuario.setNif(dto.getNif());
        usuario.setContrasena(dto.getContrasena());
        usuario.setId(dto.getId());
        this.usuarioRepository.save(usuario);
    }
    public UsuarioDTO buscarUsuario(Integer id) {
        return Objects.requireNonNull(this.usuarioRepository.findById(id).orElse(null)).toDTO();
    }
}
