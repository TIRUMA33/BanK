package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer>{
    @Query("SELECT c FROM ConversacionEntity c WHERE c.terminada = 0 and c.usuarioByEmisor.id = :id")
    public ConversacionEntity findConversacionAbiertaByUsuario(@Param("id") Integer id);
}
