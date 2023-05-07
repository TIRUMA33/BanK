package es.uma.taw.bank.service;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.dao.EmpresaClienteRepository;
import es.uma.taw.bank.dao.EmpresaRepository;
import es.uma.taw.bank.dao.PersonaRepository;
import es.uma.taw.bank.dao.TipoClienteRelacionadoRepository;
import es.uma.taw.bank.dto.EmpresaClienteDTO;
import es.uma.taw.bank.entity.EmpresaClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaClienteService {

    private EmpresaClienteRepository empresaClienteRepository;

    private EmpresaRepository empresaRepository;

    private PersonaRepository personaRepository;

    private TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository;

    @Autowired
    public void setEmpresaClienteRepository(EmpresaClienteRepository empresaClienteRepository) {
        this.empresaClienteRepository = empresaClienteRepository;
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
    public void setTipoClienteRelacionadoRepository(TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository) {
        this.tipoClienteRelacionadoRepository = tipoClienteRelacionadoRepository;
    }

    public EmpresaClienteDTO buscarTipoPorPersona(Integer id) {
        return this.empresaClienteRepository.buscarTipoPorPersona(id).toDTO();
    }

    public void guardarEmpresaCliente(EmpresaClienteDTO dto, Integer tipoClienteRelacionado, Integer empresaId,
                                      Integer personaId) {
        EmpresaClienteEntity empresaCliente = new EmpresaClienteEntity();

        if (tipoClienteRelacionado == 1) {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(2).orElse(null));
        } else {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(1).orElse(null));
        }

        empresaCliente.setEmpresaByIdEmpresa(this.empresaRepository.findById(empresaId).orElse(null));
        empresaCliente.setPersonaByIdPersona(this.personaRepository.findById(personaId).orElse(null));

        this.empresaClienteRepository.save(empresaCliente);
    }
}
