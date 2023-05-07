package es.uma.taw.bank.service;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.dao.EmpresaPersonaRepository;
import es.uma.taw.bank.dao.EmpresaRepository;
import es.uma.taw.bank.dao.PersonaRepository;
import es.uma.taw.bank.dao.TipoPersonaRelacionadaRepository;
import es.uma.taw.bank.dto.EmpresaPersonaDTO;
import es.uma.taw.bank.entity.EmpresaPersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaPersonaService {

    private EmpresaPersonaRepository empresaPersonaRepository;

    private EmpresaRepository empresaRepository;

    private PersonaRepository personaRepository;

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    @Autowired
    public void setEmpresaPersonaRepositoryRepository(EmpresaPersonaRepository empresaPersonaRepository) {
        this.empresaPersonaRepository = empresaPersonaRepository;
    }

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setTipoPersonaRelacionadaRepository(TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository) {
        this.tipoPersonaRelacionadaRepository = tipoPersonaRelacionadaRepository;
    }

    public EmpresaPersonaDTO buscarPorPersona(Integer id) {
        return empresaPersonaRepository.findByPersonaByIdPersona_Id(id).map(EmpresaPersonaEntity::toDTO).orElse(null);
    }

    public void guardarEmpresaPersona(EmpresaPersonaDTO dto, Integer empresaId, Integer personaId) {
        EmpresaPersonaEntity empresaPersona = new EmpresaPersonaEntity();

        empresaPersona.setTipoPersonaRelacionadaByIdTipo(this.tipoPersonaRelacionadaRepository.findById(dto.getTipoPersonaRelacionada()).orElse(null));
        empresaPersona.setEmpresaByIdEmpresa(this.empresaRepository.findById(empresaId).orElse(null));
        empresaPersona.setPersonaByIdPersona(this.personaRepository.findById(personaId).orElse(null));

        this.empresaPersonaRepository.save(empresaPersona);
    }

    public void borrarEmpresaPersonaPorPersona(Integer id) {
        this.empresaPersonaRepository.deleteByPersonaByIdPersona_Id(id);
    }
}
