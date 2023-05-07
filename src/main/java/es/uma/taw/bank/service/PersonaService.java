package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.PersonaRepository;
import es.uma.taw.bank.dto.PersonaDTO;
import es.uma.taw.bank.entity.DireccionEntity;
import es.uma.taw.bank.entity.PersonaEntity;
import es.uma.taw.bank.ui.FiltroEmpresaPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    protected PersonaRepository personaRepository;

    public PersonaDTO buscarPersona(Integer id) {
        PersonaEntity persona = personaRepository.findById(id).orElse(null);
        if (persona != null) return persona.toDTO();
        else return null;
    }

    public PersonaDTO buscarPersonaPorDni(String dni) {
        PersonaEntity persona = personaRepository.findByDni(dni).orElse(null);
        if (persona != null) return persona.toDTO();
        else return null;
    }

    public List<Object[]> buscarPersonasDistintasPorEmpresa(Integer empresaId, Integer personaId) {
        List<Object[]> personas = this.personaRepository.distintasPersonasPorEmpresa(empresaId, personaId);

        return this.listaArrayObjetosADTO(personas);
    }

    public List<Object[]> buscarPersonasPorEmpresa(Integer id) {
        List<Object[]> personas = this.personaRepository.personasPorEmpresa(id);

        return this.listaArrayObjetosADTO(personas);
    }

    public List<PersonaDTO> listarPersonas() {
        List<PersonaEntity> personas = this.personaRepository.findAll();

        return this.listaEntidadesADTO(personas);
    }

    public List<Object[]> filtrar(Integer empresaId, Integer personaId, FiltroEmpresaPersona filtro) {
        List<Object[]> personas;

        if (!filtro.getFechaNacimiento() && filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTexto(empresaId, personaId,
                    filtro.getTexto());
        } else if (filtro.getTexto().isBlank() && filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimiento(empresaId, personaId);
        } else if (filtro.getTexto().isBlank() && !filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTipo(empresaId, personaId, filtro.getTipo());
        } else if (filtro.getTipo().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoFechaNacimiento(empresaId, personaId,
                    filtro.getTexto());
        } else if (!filtro.getFechaNacimiento()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoTipo(empresaId, personaId,
                    filtro.getTexto(), filtro.getTipo());
        } else if (filtro.getTexto().isBlank()) {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorFechaNacimientoTipo(empresaId, personaId,
                    filtro.getTipo());
        } else {
            personas = this.personaRepository.filtrarPersonasPorEmpresaPorTextoFechaNacimientoTipo(empresaId,
                    personaId, filtro.getTexto(), filtro.getTipo());
        }

        return this.listaArrayObjetosADTO(personas);
    }

    public void guardarPersona(PersonaDTO dto) {
        PersonaEntity persona = new PersonaEntity();

        persona.setId(dto.getId());
        persona.setNombre(dto.getNombre());
        persona.setDni(dto.getDni());
        persona.setApellido1(dto.getApellido1());
        persona.setApellido2(dto.getApellido2());
        persona.setFechaNacimiento(dto.getFechaNacimiento());

        this.personaRepository.save(persona);
    }

    public void guardarPersona(PersonaDTO dto, Integer id) {
        PersonaEntity persona = new PersonaEntity();

        persona.setId(id);
        persona.setNombre(dto.getNombre());
        persona.setDni(dto.getDni());
        persona.setApellido1(dto.getApellido1());
        persona.setApellido2(dto.getApellido2());
        persona.setFechaNacimiento(dto.getFechaNacimiento());

        this.personaRepository.save(persona);
    }

    protected List<PersonaDTO> listaEntidadesADTO(List<PersonaEntity> lista) {
        List<PersonaDTO> dtos = new ArrayList<>();

        lista.forEach((final PersonaEntity persona) -> dtos.add(persona.toDTO()));

        return dtos;
    }

    protected List<Object[]> listaArrayObjetosADTO(List<Object[]> lista) {
        List<Object[]> dtos = new ArrayList<>();

        lista.forEach((final Object[] fila) -> {
            PersonaEntity persona = (PersonaEntity) fila[0];
            DireccionEntity direccion = (DireccionEntity) fila[1];
            String tipoClienteRelacionado = (String) fila[2];
            String tipoPersonaRelacionada = (String) fila[3];

            Object[] dtoArray = new Object[4];
            dtoArray[0] = persona.toDTO();
            dtoArray[1] = direccion.toDTO();
            dtoArray[2] = tipoClienteRelacionado;
            dtoArray[3] = tipoPersonaRelacionada;

            dtos.add(dtoArray);
        });

        return dtos;
    }

    public void borrarPersona(Integer id) {
        this.personaRepository.deleteById(id);
    }
}
