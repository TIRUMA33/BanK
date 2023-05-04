package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.CuentaBancoEntity;
import es.uma.taw.bank.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Integer> {
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta")
    public List<TransaccionEntity> operacionesPorCuenta(@Param("cuenta") Integer idCuenta);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta and t.cuentaBancoByCuentaOrigen.id=:cuentaFiltro) or (t.cuentaBancoByCuentaOrigen.id=:cuenta and t.cuentaBancoByCuentaDestino.id=:cuentaFiltro) order by t.fechaEjecucion desc,t.cantidad desc")
    public List<TransaccionEntity> todoFiltrado(@Param("cuenta") Integer idCuenta, @Param("cuentaFiltro") String idCuentaFiltro);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.fechaEjecucion desc,t.cantidad desc")
    public List<TransaccionEntity> soloSinCuenta(@Param("cuenta") Integer idCuenta);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.cantidad desc")
    public List<TransaccionEntity> soloCantidad(@Param("cuenta") Integer idCuenta);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.fechaEjecucion desc")
    public List<TransaccionEntity> soloFecha(@Param("cuenta") Integer idCuenta);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta and t.cuentaBancoByCuentaOrigen.id=:cuentaFiltro) or (t.cuentaBancoByCuentaOrigen.id=:cuenta and t.cuentaBancoByCuentaDestino.id=:cuentaFiltro) order by t.cantidad desc")
    public List<TransaccionEntity> cuentaYCantidad(@Param("cuenta") Integer idCuenta, @Param("cuentaFiltro") String idCuentaFiltro);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta and t.cuentaBancoByCuentaOrigen.id=:cuentaFiltro) or (t.cuentaBancoByCuentaOrigen.id=:cuenta and t.cuentaBancoByCuentaDestino.id=:cuentaFiltro) order by t.fechaEjecucion desc")
    public List<TransaccionEntity> cuentaYFecha(@Param("cuenta") Integer idCuenta, @Param("cuentaFiltro") String idCuentaFiltro);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta and t.cuentaBancoByCuentaOrigen.id=:cuentaFiltro) or (t.cuentaBancoByCuentaOrigen.id=:cuenta and t.cuentaBancoByCuentaDestino.id=:cuentaFiltro)")
    public List<TransaccionEntity> soloCuenta(@Param("cuenta") Integer idCuenta, @Param("cuentaFiltro") String idCuentaFiltro);
    @Query("select t from TransaccionEntity t order by t.fechaEjecucion desc")
    public List<TransaccionEntity> ordenarlistatransacciones();
    @Query("select c from TransaccionEntity t, CuentaBancoEntity c where DATEDIFF(CURDATE() , t.fechaEjecucion) > 30 and t.cuentaBancoByCuentaOrigen = c")
    public List<CuentaBancoEntity> listainactivos();

}
