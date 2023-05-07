package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.*;
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
    @Autowired
    protected DivisaRepository divisaRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected EntidadBancariaRepository entidadBancariaRepository;

    public void guardarcuenta(CuentaDTO dto) {
        CuentaBancoEntity cuenta;
        cuenta = new CuentaBancoEntity();
        cuenta.setId(dto.getId());
        cuenta.setSaldo(dto.getSaldo());
        cuenta.setIbanCuenta(dto.getIbanCuenta());
        cuenta.setPais(dto.getPais());
        cuenta.setFechaApertura(dto.getFechaApertura());
        cuenta.setFechaCierre(dto.getFechaCierre());
        cuenta.setSwift(dto.getSwift());
        cuenta.setEstadoCuentaByEstadoCuentaId(estadoCuentaRepository.buscarPorTipo(dto.getEstadoTipo()));
        cuenta.setClienteByTitularId(clienteRepository.findById(dto.getCliente()).orElse(null));
        cuenta.setDivisaByDivisaId(divisaRepository.buscarPorNombre(dto.getDivisaNombre()));
        cuenta.setEntidadBancariaByEntidadBancariaId(entidadBancariaRepository.findById(dto.getEntidad()).orElse(null));
        this.cuentaRepository.save(cuenta);
    }

    public List<CuentaDTO> cuentasPorCliente(Integer idCliente){
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarPorCliente(idCliente);
        return this.listaEntidadesADTO(cuentas);
    }

    public List<CuentaDTO> cuentasSinMi(Integer idCuenta){
        List<CuentaBancoEntity> cuentas = cuentaRepository.buscarSinMi(idCuenta);
        return this.listaEntidadesADTO(cuentas);
    }
    public void sumarSaldo(Integer idCuenta, Double cantidad){
        CuentaBancoEntity c = this.cuentaRepository.findById(idCuenta).orElse(null);
        c.setSaldo(c.getSaldo()+cantidad);
        cuentaRepository.save(c);
    }
    public void pedirDesbloqueo(Integer idCuenta){
        CuentaBancoEntity c = this.cuentaRepository.findById(idCuenta).orElse(null);
        EstadoCuentaEntity pendienteDesbloqueo = estadoCuentaRepository.findById(5).orElse(null);
        c.setEstadoCuentaByEstadoCuentaId(pendienteDesbloqueo);
        cuentaRepository.save(c);
    }

    public CuentaDTO buscarCuenta(Integer id){
        CuentaBancoEntity cuenta = cuentaRepository.findById(id).orElse(null);
        if(cuenta!=null)return cuenta.toDTO();
        else return null;
    }

    protected List<CuentaDTO> listaEntidadesADTO (List<CuentaBancoEntity> lista) {
        ArrayList dtos = new ArrayList<CuentaDTO>();

        lista.forEach((final CuentaBancoEntity cuenta) -> dtos.add(cuenta.toDTO()));

        return dtos;
    }


}
