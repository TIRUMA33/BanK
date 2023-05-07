package es.uma.taw.bank.dao;
//Autores Alejandro Guerra 50% David Castaños 50%
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
    @Query("select t from TransaccionEntity t order by t.fechaEjecucion")
    public List<TransaccionEntity> ordenarlistatransacciones();
    @Query("select c from TransaccionEntity t, CuentaBancoEntity c where DATEDIFF(CURDATE() , t.fechaEjecucion) > 30 and t.cuentaBancoByCuentaOrigen = c")
    public List<CuentaBancoEntity> listainactivos();
    @Query("select t from TransaccionEntity t order by t.cantidad desc")
    public List<TransaccionEntity> ordenarPorCantidad();
    @Query("select t from TransaccionEntity t where cast(t.cantidad  as string) like :texto or cast(t.cuentaBancoByCuentaDestino.ibanCuenta as string) like :texto or  CAST(t.fechaEjecucion as string)  like CONCAT('%', :texto, '%') or CAST(t.fechaInstruccion as string)  like CONCAT('%', :texto, '%') order by t.fechaEjecucion desc")
    public List<TransaccionEntity> filtrarPorTexto(@Param("texto") String texto);
    @Query("select t from TransaccionEntity t where cast(t.cantidad  as string) like :texto or cast(t.cuentaBancoByCuentaDestino.ibanCuenta as string) like :texto or CAST(t.fechaEjecucion as string) like CONCAT('%', :texto, '%') or CAST(t.fechaInstruccion as string)  like CONCAT('%', :texto, '%') order by t.cantidad desc")
    public List<TransaccionEntity> filtraPorTextoyordenarPorCantidad(@Param("texto") String texto);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta and t.cantidad=:cantidad")
    public List<TransaccionEntity> buscarporCuentaYCantidad(@Param(("cuenta")) Integer cuenta, @Param(("cantidad")) double cantidad);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta")
    public List<TransaccionEntity> buscarporCuenta(@Param("cuenta") Integer cuenta);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.cantidad asc")
    public List<TransaccionEntity> buscaryordporCuentaYCantidad(@Param("cuenta") Integer cuenta);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.fechaEjecucion asc")
    public List<TransaccionEntity> buscaryordporCuentaYFecha(@Param("cuenta") Integer cuenta);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta) and (t.cuentaBancoByCuentaOrigen.ibanCuenta like CONCAT('%', :iban, '%') or t.cuentaBancoByCuentaDestino.ibanCuenta like CONCAT('%', :iban, '%'))")
    public List<TransaccionEntity> buscarpordoblecuenta(@Param("cuenta") Integer cuenta, @Param("iban") String iban);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta) and (t.cuentaBancoByCuentaOrigen.ibanCuenta like CONCAT('%', :iban, '%') or t.cuentaBancoByCuentaDestino.ibanCuenta like CONCAT('%', :iban, '%')) order by t.cantidad desc")
    public List<TransaccionEntity> buscarporDobleCuentayCantidad(@Param("cuenta") Integer cuenta, @Param("iban") String iban);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta) and (t.cuentaBancoByCuentaOrigen.ibanCuenta like CONCAT('%', :iban, '%') or t.cuentaBancoByCuentaDestino.ibanCuenta like CONCAT('%', :iban, '%')) order by t.fechaEjecucion desc")
    public List<TransaccionEntity> buscarporDobleCuentayFecha(@Param("cuenta") Integer cuenta, @Param("iban") String iban);
    @Query("select t from TransaccionEntity t where t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta order by t.fechaEjecucion asc, t.cantidad desc")
    public List<TransaccionEntity> buscaryordporCuentaFechaYCantidad(@Param("cuenta") Integer cuenta);
    @Query("select t from TransaccionEntity t where (t.cuentaBancoByCuentaDestino.id=:cuenta or t.cuentaBancoByCuentaOrigen.id=:cuenta) and (t.cuentaBancoByCuentaOrigen.ibanCuenta like CONCAT('%', :iban, '%') or t.cuentaBancoByCuentaDestino.ibanCuenta like CONCAT('%', :iban, '%')) order by t.fechaEjecucion desc, t.cantidad desc")
    public List<TransaccionEntity> buscarporDobleCuentaFechaYCantidad(@Param("cuenta") Integer cuenta, @Param("iban") String iban);
}
