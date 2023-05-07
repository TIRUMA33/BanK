package es.uma.taw.bank.dao;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.entity.EmpresaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaClienteRepository extends JpaRepository<EmpresaClienteEntity, Integer> {
    @Query("select ec from EmpresaClienteEntity ec where ec.personaByIdPersona.id = :id")
    EmpresaClienteEntity buscarTipoPorPersona(@Param("id") Integer id);
}