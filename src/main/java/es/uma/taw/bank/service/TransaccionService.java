package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.TransaccionRepository;
import es.uma.taw.bank.dto.TransaccionDTO;
import es.uma.taw.bank.entity.TransaccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionService {
    @Autowired
    protected TransaccionRepository transaccionRepository;
    public List<TransaccionDTO> listarTransaccionesPorCuenta(Integer idCuenta){
        List<TransaccionEntity> lista = transaccionRepository.operacionesPorCuenta(idCuenta);
        return this.listaEntidadesADTO(lista);
    }

    protected List<TransaccionDTO> listaEntidadesADTO (List<TransaccionEntity> lista) {
        ArrayList dtos = new ArrayList<TransaccionDTO>();

        lista.forEach((final TransaccionEntity transaccion) -> dtos.add(transaccion.toDTO()));

        return dtos;
    }
}
