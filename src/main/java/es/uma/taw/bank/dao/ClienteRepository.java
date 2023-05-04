package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.ClienteEntity;
import es.uma.taw.bank.entity.CuentaBancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    @Query("select c from ClienteEntity c where c.estadoClienteByEstadoClienteId.tipo like 'Pendiente'")
    public List<ClienteEntity> listaPendientes();

}
