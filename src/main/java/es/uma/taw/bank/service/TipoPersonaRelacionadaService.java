package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.TipoPersonaRelacionadaRepository;
import es.uma.taw.bank.dto.TipoPersonaRelacionadaDTO;
import es.uma.taw.bank.entity.TipoPersonaRelacionadaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoPersonaRelacionadaService {

    private TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository;

    @Autowired
    public void setTipoPersonaRelacionadaRepository(TipoPersonaRelacionadaRepository tipoPersonaRelacionadaRepository) {
        this.tipoPersonaRelacionadaRepository = tipoPersonaRelacionadaRepository;
    }

    public List<TipoPersonaRelacionadaDTO> listarTipoPersonasRelacionada() {
        List<TipoPersonaRelacionadaEntity> tipoPersonaRelacionadaList = this.tipoPersonaRelacionadaRepository.findAll();
        return this.listaEntidadesADTO(tipoPersonaRelacionadaList);
    }

    protected List<TipoPersonaRelacionadaDTO> listaEntidadesADTO(List<TipoPersonaRelacionadaEntity> lista) {
        List<TipoPersonaRelacionadaDTO> dtos = new ArrayList<>();

        lista.forEach((final TipoPersonaRelacionadaEntity tipoPersonaRelacionada) -> dtos.add(tipoPersonaRelacionada.toDTO()));

        return dtos;
    }
}
