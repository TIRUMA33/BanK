package es.uma.taw.bank.dao;

/**
 * @author Óscar Fernández Díaz
 */

import es.uma.taw.bank.entity.EntidadBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadBancariaRepository extends JpaRepository<EntidadBancariaEntity, Integer> {
}