package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.ClienteEntity;
import es.uma.taw.bank.entity.EmpresaEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    @Query("select c from ClienteEntity c where c.estadoClienteByEstadoClienteId.tipo like 'Pendiente'")
    public List<ClienteEntity> listaPendientes();

    @Query ("select c from ClienteEntity c order by c.estadoClienteByEstadoClienteId.tipo")
    public List<ClienteEntity> ordenadoporestado();

    @Query ("select c from PersonaEntity c order by c.nombre")
    public List<PersonaEntity> ordenadopornombrepersona();

    @Query ("select c from EmpresaEntity c order by c.nombre")
    public List<EmpresaEntity> ordenadopornombreempresa();

    @Query("select c from PersonaEntity c where c.nombre like CONCAT('%', :filtro, '%') or c.apellido1 like CONCAT('%', :filtro, '%') or c.apellido2 like CONCAT('%', :filtro, '%') or c.dni like CONCAT('%', :filtro, '%')")
    public List<PersonaEntity> buscarPorNombrePersona (@Param("filtro") String filtro);

    @Query("select c from EmpresaEntity c where c.nombre like CONCAT('%', :filtro, '%') or c.cif like CONCAT('%', :filtro, '%')")
    public List<EmpresaEntity> buscarPorNombreEmpresa (@Param("filtro") String filtro);

    @Query("select c from PersonaEntity c where c.nombre like CONCAT('%', :filtro, '%') or c.apellido1 like CONCAT('%', :filtro, '%') or c.apellido2 like CONCAT('%', :filtro, '%') or c.dni like CONCAT('%', :filtro, '%') order by c.nombre")
    public List<PersonaEntity> buscarPorNombrePersonaordenado (@Param("filtro") String filtro);

    @Query("select c from EmpresaEntity c where c.nombre like CONCAT('%', :filtro, '%') or c.cif like CONCAT('%', :filtro, '%') order by c.nombre")
    public List<EmpresaEntity> buscarPorNombreEmpresaordenado (@Param("filtro") String filtro);

    @Query("select c from ClienteEntity c order by c.id desc LIMIT 1")
    ClienteEntity ultimoCliente();
}
