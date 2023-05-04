package es.uma.taw.bank.dao;

import es.uma.taw.bank.entity.EmpresaEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query("select p from PersonaEntity p where p.dni = :dni")
    Optional<PersonaEntity> findByDni(@Param("dni") String dni);

    @Query("select p, ep.tipoPersonaRelacionadaByIdTipo.tipo from PersonaEntity p, EmpresaPersonaEntity ep where p = "
            + "ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> personasPorEmpresa(@Param("id") String id);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id")
    List<Object[]> distintasPersonasPorEmpresa(@Param("id") String id, @Param("personaId") String personaId);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', " + ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%')))")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombre(@Param("id") String id, @Param("personaId") String personaId
            , @Param("texto") String texto);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) order by p" +
            ".fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimiento(@Param("id") String id,
                                                               @Param("personaId") String personaId);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorTipo(@Param("id") String id, @Param("personaId") String personaId,
                                                    @Param("tipo") List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', " + ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimiento(@Param("id") String id,
                                                                        @Param("personaId") String personaId, @Param(
                                                                                "texto") String texto);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', " + ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo " + "in :tipo)")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreTipo(@Param("id") String id,
                                                             @Param("personaId") String personaId,
                                                             @Param("texto") String texto,
                                                             @Param("tipo") List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorFechaNacimientoTipo(@Param("id") String id,
                                                                   @Param("personaId") String personaId, @Param("tipo"
    ) List<String> tipo);

    @Query("select p, d, ec.tipoClienteRelacionadoByIdTipo.tipo, ep.tipoPersonaRelacionadaByIdTipo.tipo from " +
            "PersonaEntity p, DireccionEntity d, EmpresaClienteEntity ec, EmpresaPersonaEntity ep where (p.id != " +
            ":personaId and p.id = d.clienteByClienteId.id and p = ec.personaByIdPersona and ec.empresaByIdEmpresa" +
            ".id" + " = :id and p = ep.personaByIdPersona and ep.empresaByIdEmpresa.id = :id) and ((p.dni like CONCAT" +
            "('%', " + ":texto, '%')) or (p.nombre like CONCAT('%', :texto, '%'))) and (ep" +
            ".tipoPersonaRelacionadaByIdTipo.tipo " + "in :tipo) order by p.fechaNacimiento")
    List<Object[]> filtrarPersonasPorEmpresaPorDniNombreFechaNacimientoTipo(@Param("id") String id, @Param("personaId"
    ) String personaId, @Param("texto") String texto, @Param("tipo") List<String> tipo);
}
