package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> personasPorEmpresa(@Param("id") int id);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', :texto, '%')) or (p.nombre like CONCAT('%', :texto, '%')))")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombre(@Param("id") int id, @Param("texto") String texto);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimiento(@Param("id") int id);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep.tipoPersonaRelacionadaByIdTipo.tipo in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorTipo(@Param("id") int id, @Param("tipo") List<String> tipo);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', :texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimiento(@Param("id") int id, @Param("texto") String texto);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', :texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep.tipoPersonaRelacionadaByIdTipo.tipo in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreTipo(@Param("id") int id, @Param("texto") String texto, @Param("tipo") List<String> tipo);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep.tipoPersonaRelacionadaByIdTipo.tipo in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimientoTipo(@Param("id") int id, @Param("tipo") List<String> tipo);

    @Query("select p, d, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, DireccionEntity d, EmpresaPersonaEntity ep where (p.id = d.clienteByClienteId.id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT('%', :texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep.tipoPersonaRelacionadaByIdTipo.tipo in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimientoTipo(@Param("id") int id, @Param("texto") String texto, @Param("tipo") List<String> tipo);
}
