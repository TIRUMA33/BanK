package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.UsuarioRepository;
import es.uma.taw.bank.dto.UsuarioDTO;
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

    public UsuarioDTO buscarUsuario(Integer id) {
        return Objects.requireNonNull(this.usuarioRepository.findById(id).orElse(null)).toDTO();
    }
}
