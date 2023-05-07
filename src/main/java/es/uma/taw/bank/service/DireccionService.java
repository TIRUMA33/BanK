package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.ClienteRepository;
import es.uma.taw.bank.dao.DireccionRepository;
import es.uma.taw.bank.dto.ClienteDTO;
import es.uma.taw.bank.dto.DireccionDTO;
import es.uma.taw.bank.entity.DireccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {

    private ClienteRepository clienteRepository;

    private DireccionRepository direccionRepository;

    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Autowired
    public void setDireccionRepository(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public void guardarDireccion(DireccionDTO dto, Integer clienteId, boolean valida){
        DireccionEntity direccion = new DireccionEntity();

        direccion.setId(dto.getId());
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setPlantaPuertaOficina(dto.getPlantaPuertaOficina());
        direccion.setCiudad(dto.getCiudad());
        direccion.setRegion(dto.getRegion());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setPais(dto.getPais());
        direccion.setValida((byte) (valida ? 1 : 0));
        direccion.setClienteByClienteId(this.clienteRepository.findById(clienteId).orElse(null));

        this.direccionRepository.save(direccion);
    }
    public DireccionDTO buscarPorCliente(Integer id) {
        return direccionRepository.findByClienteByClienteId_Id(id)
                .map(DireccionEntity::toDTO)
                .orElse(null);
    }

    public void borrarDireccionPorCliente(Integer id) {
        this.direccionRepository.deleteByClienteByClienteId_Id(id);
    }
}
