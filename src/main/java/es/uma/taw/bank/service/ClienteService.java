package es.uma.taw.bank.service;
//Autores Alejandro Guerra 40% Óscar Fernández 50% Pablo Ruiz 10%

import es.uma.taw.bank.dao.ClienteRepository;
import es.uma.taw.bank.dao.EstadoClienteRepository;
import es.uma.taw.bank.dto.ClienteDTO;
import es.uma.taw.bank.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ClienteService {
    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EstadoClienteRepository estadoClienteRepository;

    public ClienteDTO buscarCliente(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

    public ClienteDTO ultimoCliente() {
        ClienteEntity cliente = clienteRepository.ultimoCliente();
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

    public void guardarCliente(ClienteDTO dto) {
        ClienteEntity cliente = new ClienteEntity();

        cliente.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        cliente.setEstadoClienteByEstadoClienteId(estadoClienteRepository.findById(5).orElse(null));

        this.clienteRepository.save(cliente);
    }

    public void borrarCliente(Integer id) {
        this.clienteRepository.deleteById(id);
    }
}
