package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
}
