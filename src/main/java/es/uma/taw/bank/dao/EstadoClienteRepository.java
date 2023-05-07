package es.uma.taw.bank.dao;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.entity.EstadoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoClienteRepository extends JpaRepository<EstadoClienteEntity, Integer> {
}