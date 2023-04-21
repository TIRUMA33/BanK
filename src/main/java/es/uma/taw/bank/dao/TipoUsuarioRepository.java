package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {
}