package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer> {
}