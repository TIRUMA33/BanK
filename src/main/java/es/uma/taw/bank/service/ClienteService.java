package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.ClienteRepository;
import es.uma.taw.bank.dto.ClienteDTO;
import es.uma.taw.bank.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    protected ClienteRepository clienteRepository;

    public ClienteDTO buscarCliente(Integer id){
        ClienteEntity cliente = clienteRepository.findById(id).get();
        if(cliente!=null){
            return cliente.toDTO();
        }
        else{
            return null;
        }
    }
}
