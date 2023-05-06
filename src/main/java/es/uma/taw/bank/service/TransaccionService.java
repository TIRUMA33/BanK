package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.CuentaRepository;
import es.uma.taw.bank.dao.TransaccionRepository;
import es.uma.taw.bank.dto.CuentaDTO;
import es.uma.taw.bank.dto.TransaccionDTO;
import es.uma.taw.bank.entity.TransaccionEntity;
import es.uma.taw.bank.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionService {
    @Autowired
    protected TransaccionRepository transaccionRepository;
    @Autowired
    protected CuentaRepository cuentaRepository;
    public List<TransaccionDTO> listarTransaccionesPorCuenta(Integer idCuenta){
        List<TransaccionEntity> lista = transaccionRepository.operacionesPorCuenta(idCuenta);
        return this.listaEntidadesADTO(lista);
    }

    public TransaccionDTO iniciarTransaccion(Integer idCuentaOrigen){
        TransaccionDTO dto = new TransaccionDTO();
        dto.setCuentaOrigen(idCuentaOrigen);
        return dto;
    }

    public void guardarTransaccion(TransaccionDTO dto){
        TransaccionEntity t = new TransaccionEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        t.setFechaEjecucion(timestamp);
        t.setFechaInstruccion(timestamp);
        t.setCuentaBancoByCuentaOrigen(this.cuentaRepository.findById(dto.getCuentaOrigen()).orElse(null));
        t.setId(dto.getId());
        t.setCantidad(dto.getCantidad());
        t.setCuentaBancoByCuentaDestino(this.cuentaRepository.findById(dto.getCuentaDestino()).orElse(null));

        this.transaccionRepository.save(t);
    }

    protected List<TransaccionDTO> listaEntidadesADTO (List<TransaccionEntity> lista) {
        ArrayList dtos = new ArrayList<TransaccionDTO>();

        lista.forEach((final TransaccionEntity transaccion) -> dtos.add(transaccion.toDTO()));

        return dtos;
    }
    public List<TransaccionDTO> filtrar(Integer idCuenta, FiltroOperaciones filtro){
        List<TransaccionEntity> operaciones=transaccionRepository.operacionesPorCuenta(idCuenta);
        if(filtro.getCantidad() && filtro.getFechaEjecucion() && filtro.getCuentaFiltro()!=null && !filtro.getCuentaFiltro().equals("")){
            operaciones=transaccionRepository.todoFiltrado(idCuenta,filtro.getCuentaFiltro());
        }
        else if(filtro.getCantidad() && !filtro.getFechaEjecucion() && filtro.getCuentaFiltro()!=null && !filtro.getCuentaFiltro().equals("")){
            operaciones = transaccionRepository.cuentaYCantidad(idCuenta,filtro.getCuentaFiltro());
        }
        else if(!filtro.getCantidad() && filtro.getFechaEjecucion() && filtro.getCuentaFiltro()!=null && !filtro.getCuentaFiltro().equals("")){
            operaciones = transaccionRepository.cuentaYFecha(idCuenta,filtro.getCuentaFiltro());
        }
        else if(!filtro.getCantidad() && !filtro.getFechaEjecucion() && filtro.getCuentaFiltro()!=null && !filtro.getCuentaFiltro().equals("")){
            operaciones = transaccionRepository.soloCuenta(idCuenta,filtro.getCuentaFiltro());
        }
        else if(filtro.getCantidad() && filtro.getFechaEjecucion() && (filtro.getCuentaFiltro()==null || filtro.getCuentaFiltro().equals(""))){
            operaciones=transaccionRepository.soloSinCuenta(idCuenta);
        }
        else if(filtro.getCantidad() && !filtro.getFechaEjecucion() && (filtro.getCuentaFiltro()==null || filtro.getCuentaFiltro().equals(""))){
            operaciones = transaccionRepository.soloCantidad(idCuenta);
        }
        else if(!filtro.getCantidad() && filtro.getFechaEjecucion() && (filtro.getCuentaFiltro()==null || filtro.getCuentaFiltro().equals(""))){
            operaciones = transaccionRepository.soloFecha(idCuenta);
        }
        else if(!filtro.getCantidad() && !filtro.getFechaEjecucion() && (filtro.getCuentaFiltro()==null || filtro.getCuentaFiltro().equals(""))){
            operaciones=transaccionRepository.operacionesPorCuenta(idCuenta);
        }
        return this.listaEntidadesADTO(operaciones);
    }

    public List<CuentaDTO> rellenarCuentas(Integer idCuenta, List<TransaccionDTO> operaciones) {
        List<CuentaDTO> cuentas = new ArrayList<>();
        for(TransaccionDTO t: operaciones){
            CuentaDTO cuentaDestino = cuentaRepository.findById(t.getCuentaDestino()).orElse(null).toDTO();
            CuentaDTO cuentaOrigen = cuentaRepository.findById(t.getCuentaOrigen()).orElse(null).toDTO();
            if(!cuentas.contains(cuentaDestino))if(cuentaDestino.getId()!=idCuenta)cuentas.add(cuentaDestino);
            if(!cuentas.contains(cuentaOrigen))if(cuentaOrigen.getId()!=idCuenta)cuentas.add(cuentaOrigen);
        }
        return cuentas;
    }
}
