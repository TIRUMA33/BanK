package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer> {
    @Query
    void deleteByClienteByClienteId_Id(@Param("id") int id);
}