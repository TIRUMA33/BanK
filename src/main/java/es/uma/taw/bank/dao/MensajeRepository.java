package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
    @Query("SELECT m FROM MensajeEntity m WHERE m.usuarioByEmisor.id=1")
    public List<MensajeEntity> findClienteMensajes();

    @Query("SELECT m FROM MensajeEntity m WHERE m.usuarioByEmisor.id=2")
    public List<MensajeEntity> findAsistenteMensajes();

    @Query
    public MensajeEntity findByUsuarioByEmisor(@Param("id") Integer id);
}
