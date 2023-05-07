package es.uma.taw.bank.dao;
//Autores Pablo Robles Mansilla 50%

import es.uma.taw.bank.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.nif = :nif and u.contrasena = :contrasena")
    UsuarioEntity autenticar(@Param("nif") String nif, @Param("contrasena") String contrasena);

    @Query("select u from UsuarioEntity u where u.tipoUsuarioByTipoUsuario.id = 3")
    UsuarioEntity findAsistente();
}
