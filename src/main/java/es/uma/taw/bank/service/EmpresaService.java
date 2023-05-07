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

    //Hago un buscar por id oscar tu ya lo arreglas pero es para poder iniciar sesion, espero te sirva:)
    public EmpresaDTO buscarEmpresaPorCif(String cif) {
        return Objects.requireNonNull(this.empresaRepository.findByCif(cif).orElse(null)).toDTO();
    }
}
