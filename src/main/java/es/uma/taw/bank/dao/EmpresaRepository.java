package es.uma.taw.bank.dao;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {
    @Query
    Optional<EmpresaEntity> findByCif(@Param("cif") String cif);

    @Query("select e from EmpresaEntity e order by e.id desc LIMIT 1")
    EmpresaEntity ultimaEmpresa();
}
