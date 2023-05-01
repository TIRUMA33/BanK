package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer> {
    @Query
    Optional<DireccionEntity> findByClienteByClienteId_Id(@Param("id") int id);

    @Query
    void deleteByClienteByClienteId_Id(@Param("id") int id);
}