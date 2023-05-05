package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
    @Query("SELECT m FROM MensajeEntity m WHERE m.usuarioByEmisor.id = :idcliente and m.conversacionByConversacion.id = :idconver order by m.fecha")
    public List<MensajeEntity> findClienteMensajes(@Param("idcliente")Integer idcliente, @Param("idconver") Integer idconver);

    @Query("SELECT m FROM MensajeEntity m WHERE m.usuarioByEmisor.id=27 and m.conversacionByConversacion.id=:idconver order by m.fecha")
    public List<MensajeEntity> findAsistenteMensajes(@Param("idconver") Integer idconver);

    @Query("SELECT m FROM MensajeEntity m WHERE m.conversacionByConversacion.id = :id order by m.fecha")
    public List<MensajeEntity> findMensajesByConversacion(@Param("id") Integer id);

    @Query
    public MensajeEntity findByUsuarioByEmisor(@Param("id") Integer id);
}
