package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.OperacionEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from EmpresaPersonaEntity ep, OperacionEntity o, TransaccionEntity t where o" +
            ".transaccionByTransaccionId = t and t.cuentaBancoByCuentaOrigen in :cuentas and ep.empresaByIdEmpresa" +
            ".id = :id")
    List<OperacionEntity> buscarPorCuentasYEmpresa(@Param("cuentas") List<CuentaBancoEntity> cuentas,
                                                   @Param("id") Integer id);

    @Query("select o from OperacionEntity o where o.personaByPersonaId in :persona")
    List<OperacionEntity> filtrarPorPersona(@Param("persona") List<PersonaEntity> persona);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t and (t" +
            ".cuentaBancoByCuentaOrigen.ibanCuenta = :cuentaBanco or t.cuentaBancoByCuentaDestino.ibanCuenta = " +
            ":cuentaBanco)")
    List<OperacionEntity> filtrarPorCuenta(@Param("cuentaBanco") String cuentaBanco);

    @Query
    List<OperacionEntity> findByTransaccionByTransaccionId_Cantidad(@Param("cantidad") Double cantidad);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t order by t" +
            ".fechaEjecucion")
    List<OperacionEntity> filtrarPorFechaEjecucion();

    @Query("select c from CuentaBancoEntity c, OperacionEntity o, TransaccionEntity t where o" +
            ".transaccionByTransaccionId = t and (c = t.cuentaBancoByCuentaOrigen or c = t.cuentaBancoByCuentaDestino)")
    List<CuentaBancoEntity> listarTodasCuentasTransaccion();
}