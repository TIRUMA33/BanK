package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.EmpresaRepository;
import es.uma.taw.bank.dto.EmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaDTO buscarEmpresa(Integer id) {
        return Objects.requireNonNull(this.empresaRepository.findById(id).orElse(null)).toDTO();
    }
}
