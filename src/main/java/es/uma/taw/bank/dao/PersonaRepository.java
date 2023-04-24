package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
}
