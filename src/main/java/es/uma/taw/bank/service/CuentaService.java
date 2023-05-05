package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.CuentaRepository;
import es.uma.taw.bank.dao.EstadoClienteRepository;
import es.uma.taw.bank.dao.EstadoCuentaRepository;
import es.uma.taw.bank.dto.CuentaDTO;
import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.EstadoCuentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    protected CuentaRepository cuentaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    public List<CuentaDTO> cuentasPorCliente(Integer idCliente){
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarPorCliente(idCliente);
        return this.listaEntidadesADTO(cuentas);
    }

    public List<CuentaDTO> cuentasSinMi(Integer idCuenta){
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarSinMi(idCuenta);
        return this.listaEntidadesADTO(cuentas);
    }
    public void sumarSaldo(Integer idCuenta, Double cantidad){
        CuentaBancoEntity c = this.cuentaRepository.findById(idCuenta).get();
        c.setSaldo(c.getSaldo()+cantidad);
        cuentaRepository.save(c);
    }
    public void pedirDesbloqueo(Integer idCuenta){
        CuentaBancoEntity c = this.cuentaRepository.findById(idCuenta).get();
        EstadoCuentaEntity pendienteDesbloqueo = estadoCuentaRepository.findById(5).get();
        c.setEstadoCuentaByEstadoCuentaId(pendienteDesbloqueo);
        cuentaRepository.save(c);
    }

    public CuentaDTO buscarCuenta(Integer id){
        CuentaBancoEntity cuenta = cuentaRepository.findById(id).get();
        if(cuenta!=null)return cuenta.toDTO();
        else return null;
    }

    protected List<CuentaDTO> listaEntidadesADTO (List<CuentaBancoEntity> lista) {
        ArrayList dtos = new ArrayList<CuentaDTO>();

        lista.forEach((final CuentaBancoEntity cuenta) -> dtos.add(cuenta.toDTO()));

        return dtos;
    }
}
