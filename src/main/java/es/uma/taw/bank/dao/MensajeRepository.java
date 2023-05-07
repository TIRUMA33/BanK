package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {

    @Query("SELECT m FROM MensajeEntity m WHERE m.conversacionByConversacion.id = :id order by m.fecha")
    public List<MensajeEntity> findMensajesByConversacion(@Param("id") Integer id);

    @Query("SELECT m FROM MensajeEntity m WHERE m.usuarioByEmisor.id = :id order by m.fecha")
    public List<MensajeEntity> findMensajesByEmisor(@Param("id") Integer id);

}
