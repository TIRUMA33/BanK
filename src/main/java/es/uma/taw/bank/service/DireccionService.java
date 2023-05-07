package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.DireccionRepository;
import es.uma.taw.bank.dto.DireccionDTO;
import es.uma.taw.bank.entity.DireccionEntity;
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

    public void guardarDireccion(DireccionDTO dto){
        DireccionEntity direccion;
        direccion=new DireccionEntity();
        direccion.setId(dto.getId());
        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setNumero(dto.getNumero());
        direccion.setRegion(dto.getRegion());
        direccion.setPlantaPuertaOficina(dto.getPlantaPuertaOficina());
        direccion.setValida(dto.getValida());
        this.direccionRepository.save(direccion);
    }
    public DireccionDTO buscarPorCliente(Integer id) {
        return Objects.requireNonNull(this.direccionRepository.findByClienteByClienteId_Id(id).orElse(null)).toDTO();
    }

    public void guardarDireccion(DireccionDTO dto, boolean valida) {
        DireccionEntity direccion = new DireccionEntity();

        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setPlantaPuertaOficina(dto.getPlantaPuertaOficina());
        direccion.setCiudad(dto.getCiudad());
        direccion.setRegion(dto.getRegion());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPais(dto.getPais());
        direccion.setValida((byte) (valida ? 1 : 0));

        this.direccionRepository.save(direccion);
    }

    public void borrarDireccionPorCliente(Integer id) {
        this.direccionRepository.deleteByClienteByClienteId_Id(id);
    }
}
