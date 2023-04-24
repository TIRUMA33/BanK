package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity,Integer> {
    @Query("select t from TransaccionEntity t order by t.fechaEjecucion")
    public List<TransaccionEntity> ordenarlistatransacciones();
}
