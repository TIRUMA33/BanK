package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.EmpresaPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaPersonaRepository extends JpaRepository<EmpresaPersonaEntity, Integer> {
}