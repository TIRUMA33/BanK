package es.uma.taw.bank.dao;
//Autor Alejandro Guerra
import es.uma.taw.bank.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {
    @Query("select e from EstadoCuentaEntity e where e.tipo=:tipo")
    public EstadoCuentaEntity buscarPorTipo(@Param("tipo") String tipo);
}
