package es.uma.taw.bank.dao;
//Autores Alejandro Guerra 33% Óscar Fernández 33% David Castaños 33%
import es.uma.taw.bank.entity.CuentaBancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<CuentaBancoEntity, Integer> {
    @Query("select c from CuentaBancoEntity c where c.clienteByTitularId.id = :cliente")
    public List<CuentaBancoEntity> buscarPorCliente(@Param("cliente") Integer clienteId);

    @Query("select c from CuentaBancoEntity c where c.id!=:cuentaId")
    public List<CuentaBancoEntity> buscarSinMi(@Param("cuentaId") Integer cuentaId);

    @Query("select c from CuentaBancoEntity c, OperacionEntity o, TransaccionEntity t where o" +
            ".transaccionByTransaccionId = t and c = t.cuentaBancoByCuentaDestino and c not in :cuentas")
    List<CuentaBancoEntity> listarTodasCuentasTransaccion(@Param("cuentas") List<CuentaBancoEntity> cuentas);
}
