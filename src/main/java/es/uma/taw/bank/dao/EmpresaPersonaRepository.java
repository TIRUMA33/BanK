package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.EmpresaPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaPersonaRepository extends JpaRepository<EmpresaPersonaEntity, Integer> {
    @Query
    Optional<EmpresaPersonaEntity> findByPersonaByIdPersona_Id(@Param("personaId") int personaId);

    @Modifying
    @Query
    void deleteByPersonaByIdPersona_Id(@Param("personaId") int personaId);
}