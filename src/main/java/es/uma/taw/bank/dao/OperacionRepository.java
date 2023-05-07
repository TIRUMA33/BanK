package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.OperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperacionRepository extends JpaRepository<OperacionEntity, Integer> {
    @Query("select o from EmpresaPersonaEntity ep, OperacionEntity o, TransaccionEntity t where o" +
            ".transaccionByTransaccionId = t and t.cuentaBancoByCuentaOrigen in :cuentas and ep.empresaByIdEmpresa.id" +
            " = :id")
    List<OperacionEntity> buscarPorCuentasYEmpresa(@Param("cuentas") List<CuentaBancoEntity> cuentas,
                                                   @Param("id") Integer id);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t and t" +
            ".cuentaBancoByCuentaDestino.ibanCuenta like concat('%', :iban, '%')")
    List<OperacionEntity> buscarPorCuenta(@Param("iban") String iban);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t order by t" +
            ".cantidad")
    List<OperacionEntity> ordenarPorCantidad();

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t order by t" +
            ".fechaEjecucion")
    List<OperacionEntity> ordenarPorFechaEjecucion();

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t order by t" +
            ".cantidad, t.fechaEjecucion")
    List<OperacionEntity> ordenarPorCantidadYFechaEjecucion();

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t and t" +
            ".cuentaBancoByCuentaDestino.ibanCuenta like concat('%', :iban, '%') order by t.fechaEjecucion")
    List<OperacionEntity> buscarPorCuentaYOrdenarPorFechaEjecucion(@Param("iban") String iban);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t and t" +
            ".cuentaBancoByCuentaDestino.ibanCuenta like concat('%', :iban, '%') order by t.cantidad")
    List<OperacionEntity> buscarPorCuentaYOrdenarPorCantidad(@Param("iban") String iban);

    @Query("select o from OperacionEntity o, TransaccionEntity t where o.transaccionByTransaccionId = t and t" +
            ".cuentaBancoByCuentaDestino.ibanCuenta like concat('%', :iban, '%') order by t.cantidad, t.fechaEjecucion")
    List<OperacionEntity> buscarPorCuentaYOrdenarPorCantidadYFechaEjecucion(@Param("iban") String iban);
}