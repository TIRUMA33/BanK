package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.EmpresaRepository;
import es.uma.taw.bank.dto.EmpresaDTO;
import es.uma.taw.bank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    @Autowired
    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaDTO> listarEmpresas() {
        List<EmpresaEntity> empresas = this.empresaRepository.findAll();

        return this.listaEntidadesADTO(empresas);
    }

    public EmpresaDTO buscarEmpresa(Integer id) {
        return empresaRepository.findById(id)
                .map(EmpresaEntity::toDTO)
                .orElse(null);
    }

    public EmpresaDTO buscarEmpresaPorCif(String cif) {
        return Objects.requireNonNull(this.empresaRepository.findByCif(cif).orElse(null)).toDTO();
    }

    public void guardarEmpresa(EmpresaDTO dto) {
        EmpresaEntity empresa = new EmpresaEntity();

        empresa.setCif(dto.getCif());
        empresa.setNombre(dto.getNombre());

        this.empresaRepository.save(empresa);
    }

    private List<EmpresaDTO> listaEntidadesADTO(List<EmpresaEntity> lista) {
        List<EmpresaDTO> dtos = new ArrayList<>();

        lista.forEach((final EmpresaEntity empresa) -> dtos.add(empresa.toDTO()));

        return dtos;
    }
}
