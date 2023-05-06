package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer>{
    @Query("SELECT c FROM ConversacionEntity c WHERE c.terminada = 0 and c.usuarioByEmisor.id = :id")
    public ConversacionEntity findConversacionAbiertaByUsuario(@Param("id") Integer id);

    @Query("SELECT c FROM ConversacionEntity c WHERE c.usuarioByEmisor.nif like CONCAT('%', :nif, '%')")
    public List<ConversacionEntity> findByNif(@Param("nif") String nif);

    @Query("SELECT c FROM ConversacionEntity c WHERE c.terminada = :status")
    public List<ConversacionEntity> findByEstado(@Param("status") Byte status);

    @Query("SELECT c FROM ConversacionEntity c order by c.fechaCreacion")
    public List<ConversacionEntity> orderByFecha();

    @Query("SELECT c FROM ConversacionEntity c order by c.terminada desc")
    public List<ConversacionEntity> orderByEstado();

    @Query("SELECT c FROM ConversacionEntity c order by c.terminada desc, c.fechaCreacion")
    public List<ConversacionEntity> orderByFechayEstado();

    @Query("SELECT c FROM ConversacionEntity c WHERE c.usuarioByEmisor.nif like CONCAT('%', :nif, '%') order by c.terminada desc")
    public List<ConversacionEntity> findByNifOrderByEstado(@Param("nif") String nif);

    @Query("SELECT c FROM ConversacionEntity c WHERE c.terminada = :status order by c.fechaCreacion")
    public List<ConversacionEntity> findByEstadoOrderByFecha(@Param("status") Byte status);

    @Query("SELECT c FROM ConversacionEntity c WHERE c.usuarioByEmisor.nif like CONCAT('%', :nif, '%') and c.terminada = :status")
    public List<ConversacionEntity> findByNifAndEstado(@Param("nif") String nif, @Param("status") Byte status);

    @Query("SELECT c FROM ConversacionEntity c WHERE c.usuarioByEmisor.nif like CONCAT('%', :nif, '%') and c.terminada = :status order by c.terminada desc, c.fechaCreacion")
    public List<ConversacionEntity> findByNifAndEstadoOrderByFechayEstado(@Param("nif") String nif, @Param("status") Byte status);
}
