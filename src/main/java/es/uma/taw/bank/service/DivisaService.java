package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.DivisaRepository;
import es.uma.taw.bank.dto.CuentaDTO;
import es.uma.taw.bank.dto.DivisaDTO;
import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.DivisaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisaService {
    @Autowired
    protected DivisaRepository divisaRepository;

    public DivisaDTO buscarDivisaPorNombre(String nombre){
        DivisaEntity d = divisaRepository.buscarPorNombre(nombre);
        if(d!=null)return d.toDTO();
        else return null;
    }
    public DivisaDTO findById(Integer id){
        DivisaEntity d = divisaRepository.findById(id).orElse(null);
        if(d!=null)return d.toDTO();
        else return null;
    }
    public List<DivisaDTO> buscarSinMi(String idDivisa){
        List<DivisaEntity> divisas = divisaRepository.buscarSinMiPorNombre(idDivisa);
        return this.listaEntidadesADTO(divisas);
    }
    public List<DivisaDTO> listarDivisas(){
        List<DivisaEntity> divisas = divisaRepository.findAll();
        return this.listaEntidadesADTO(divisas);
    }

    protected List<DivisaDTO> listaEntidadesADTO (List<DivisaEntity> lista) {
        ArrayList dtos = new ArrayList<DivisaDTO>();

        lista.forEach((final DivisaEntity divisa) -> dtos.add(divisa.toDTO()));

        return dtos;
    }
}
