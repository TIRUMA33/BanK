package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.EstadoCuentaRepository;
import es.uma.taw.bank.dto.EstadoCuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EstadoCuentaService {

    private EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    public void setEstadoCuentaRepository(EstadoCuentaRepository estadoCuentaRepository) {
        this.estadoCuentaRepository = estadoCuentaRepository;
    }

    public EstadoCuentaDTO buscarEstadoCuenta(Integer id) {
        return Objects.requireNonNull(this.estadoCuentaRepository.findById(4).orElse(null)).toDTO();
    }
}
