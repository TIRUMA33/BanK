package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {
}
