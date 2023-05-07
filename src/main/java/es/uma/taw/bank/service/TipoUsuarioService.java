package es.uma.taw.bank.service;
//Pablo Ruiz Galianez
import es.uma.taw.bank.dao.TipoUsuarioRepository;
import es.uma.taw.bank.dto.TipoUsuarioDTO;
import es.uma.taw.bank.entity.TipoUsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService {
    @Autowired
    protected TipoUsuarioRepository tipoUsuarioRepository;
    public TipoUsuarioDTO buscarTipoUsuario(Integer id){
        TipoUsuarioEntity tipo = tipoUsuarioRepository.findById(id).orElse(null);
        return tipo.toDTO();
    }
}
