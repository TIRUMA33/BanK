package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.TipoClienteRelacionadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteRelacionadoRepository extends JpaRepository<TipoClienteRelacionadoEntity, Integer> {
}