package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query(value = "insert into usuario (nif, contrasena) values (:nif, :contrasena)", nativeQuery = true)
    UsuarioEntity registrar(@Param ("nif") String nif, @Param("contrasena") String contrasena);
}
