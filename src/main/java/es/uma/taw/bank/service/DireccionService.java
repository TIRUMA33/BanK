package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.DireccionRepository;
import es.uma.taw.bank.dto.DireccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DireccionService {

    private DireccionRepository direccionRepository;

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public DireccionDTO buscarPorCliente(Integer id) {
        return Objects.requireNonNull(this.direccionRepository.findByClienteByClienteId_Id(id).orElse(null)).toDTO();
    }
}
