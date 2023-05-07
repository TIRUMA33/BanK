package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.EmpresaClienteRepository;
import es.uma.taw.bank.dao.TipoClienteRelacionadoRepository;
import es.uma.taw.bank.dto.EmpresaClienteDTO;
import es.uma.taw.bank.entity.EmpresaClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaClienteService {

    private EmpresaClienteRepository empresaClienteRepository;

    private TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository;

    @Autowired
    public void setEmpresaClienteRepository(EmpresaClienteRepository empresaClienteRepository) {
        this.empresaClienteRepository = empresaClienteRepository;
    }

    @Autowired
    public void setTipoClienteRelacionadoRepository(TipoClienteRelacionadoRepository tipoClienteRelacionadoRepository) {
        this.tipoClienteRelacionadoRepository = tipoClienteRelacionadoRepository;
    }

    public EmpresaClienteDTO buscarTipoPorPersona(Integer id) {
        return this.empresaClienteRepository.buscarTipoPorPersona(id).toDTO();
    }

    public void guardarEmpresaCliente(EmpresaClienteDTO dto) {
        EmpresaClienteEntity empresaCliente = new EmpresaClienteEntity();

        if (dto.getTipoClienteRelacionado() == 1) {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(2).orElse(null));
        } else {
            empresaCliente.setTipoClienteRelacionadoByIdTipo(this.tipoClienteRelacionadoRepository.findById(1).orElse(null));
        }

        this.empresaClienteRepository.save(empresaCliente);
    }
}
