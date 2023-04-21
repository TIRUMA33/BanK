package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.EstadoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoClienteRepository extends JpaRepository<EstadoClienteEntity, Integer> {
}