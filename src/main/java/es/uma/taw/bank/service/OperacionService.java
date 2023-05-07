package es.uma.taw.bank.service;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.dao.CuentaRepository;
import es.uma.taw.bank.dao.OperacionRepository;
import es.uma.taw.bank.dao.PersonaRepository;
import es.uma.taw.bank.dao.TransaccionRepository;
import es.uma.taw.bank.dto.OperacionDTO;
import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.OperacionEntity;
import es.uma.taw.bank.ui.FiltroOperacionesEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionService {

    private CuentaRepository cuentaRepository;

    private OperacionRepository operacionRepository;

    private PersonaRepository personaRepository;

    private TransaccionRepository transaccionRepository;

    @Autowired
    public void setCuentaRepository(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Autowired
    public void setOperacionRepository(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
    }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<OperacionDTO> buscarOperacionesPorCuentasYEmpresa(Integer empresaId) {
        List<CuentaBancoEntity> cuentas = this.cuentaRepository.buscarPorCliente(empresaId);
        List<OperacionEntity> operaciones = this.operacionRepository.buscarPorCuentasYEmpresa(cuentas, empresaId);

        return this.listaEntidadesADTO(operaciones);
    }

    public List<OperacionDTO> filtrar(FiltroOperacionesEmpresa filtro) {
        List<OperacionEntity> operaciones;

        if (!filtro.getCantidad() && !filtro.getFechaEjecucion()) {
            operaciones = this.operacionRepository.buscarPorCuenta(filtro.getCuenta());
        } else if (filtro.getCuenta().isBlank() && !filtro.getFechaEjecucion()) {
            operaciones = this.operacionRepository.ordenarPorCantidad();
        } else if (filtro.getCuenta().isBlank() && !filtro.getCantidad()) {
            operaciones = this.operacionRepository.ordenarPorFechaEjecucion();
        } else if (filtro.getCuenta().isBlank()) {
            operaciones = this.operacionRepository.ordenarPorCantidadYFechaEjecucion();
        } else if (!filtro.getCantidad()) {
            operaciones = this.operacionRepository.buscarPorCuentaYOrdenarPorFechaEjecucion(filtro.getCuenta());
        } else if (!filtro.getFechaEjecucion()) {
            operaciones = this.operacionRepository.buscarPorCuentaYOrdenarPorCantidad(filtro.getCuenta());
        } else {
            operaciones =
                    this.operacionRepository.buscarPorCuentaYOrdenarPorCantidadYFechaEjecucion(filtro.getCuenta());
        }

        return this.listaEntidadesADTO(operaciones);
    }

    public void guardarOperacion(OperacionDTO dto) {
        OperacionEntity operacion = new OperacionEntity();

        operacion.setPersonaByPersonaId(this.personaRepository.findById(dto.getPersona()).orElse(null));
        operacion.setTransaccionByTransaccionId(this.transaccionRepository.findById(dto.getTransaccion()).orElse(null));

        this.operacionRepository.save(operacion);
    }

    private List<OperacionDTO> listaEntidadesADTO(List<OperacionEntity> lista) {
        List<OperacionDTO> dtos = new ArrayList<>();

        lista.forEach((final OperacionEntity operacion) -> dtos.add(operacion.toDTO()));

        return dtos;
    }
}
