package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    @Query("select p, e.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, EmpresaPersonaEntity e where p = e.personaByIdPersona and e.empresaByIdEmpresa.id = :id")
    List<Object[]> personasPorEmpresa(@Param("id") int id);

}
