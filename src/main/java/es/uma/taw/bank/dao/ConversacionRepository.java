package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer>{
}
