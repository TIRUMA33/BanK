package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.EmpresaPersonaRepository;
import es.uma.taw.bank.dao.TipoPersonaRelacionadaRepository;
import es.uma.taw.bank.dto.EmpresaPersonaDTO;
import es.uma.taw.bank.entity.EmpresaPersonaEntity;
import es.uma.taw.bank.entity.TipoPersonaRelacionadaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmpresaPersonaService {

    private EmpresaPersonaRepository empresaPersonaRepository;

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    @Autowired
    public void setEmpresaPersonaRepositoryRepository(EmpresaPersonaRepository empresaPersonaRepository) {
        this.empresaPersonaRepository = empresaPersonaRepository;
    }

    @Autowired
    public void setTipoPersonaRelacionadaRepository(TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository) {
        this.tipoPersonaRelacionadaRepository = tipoPersonaRelacionadaRepository;
    }

    public EmpresaPersonaDTO buscarPorPersona(Integer id) {
        return Objects.requireNonNull(this.empresaPersonaRepository.findByPersonaByIdPersona_Id(id).orElse(null)).toDTO();
    }

    public void guardarEmpresaPersona(EmpresaPersonaDTO dto) {
        EmpresaPersonaEntity empresaPersona = new EmpresaPersonaEntity();

        TipoPersonaRelacionadaEntity tipoPersonaRelacionada =
                this.tipoPersonaRelacionadaRepository.findById(dto.getTipoPersonaRelacionada()).orElse(null);
        empresaPersona.setTipoPersonaRelacionadaByIdTipo(tipoPersonaRelacionada);

        this.empresaPersonaRepository.save(empresaPersona);
    }

    public void borrarEmpresaPersonaPorPersona(Integer id) {
        this.empresaPersonaRepository.deleteByPersonaByIdPersona_Id(id);
    }
}
