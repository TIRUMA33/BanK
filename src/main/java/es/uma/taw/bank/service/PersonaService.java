package es.uma.taw.bank.service;

import es.uma.taw.bank.dao.PersonaRepository;
import es.uma.taw.bank.dto.PersonaDTO;
import es.uma.taw.bank.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    protected PersonaRepository personaRepository;

    public PersonaDTO buscarPersona(Integer id){
        PersonaEntity persona = personaRepository.findById(id).orElse(null);
        if(persona!=null)return persona.toDTO();
        else return null;
    }

    public void guardarPersona(PersonaDTO dto){
        PersonaEntity persona;
        persona=new PersonaEntity();
        persona.setId(dto.getId());
        persona.setNombre(dto.getNombre());
        persona.setDni(dto.getDni());
        persona.setApellido1(dto.getApellido1());
        persona.setApellido2(dto.getApellido2());
        persona.setFechaNacimiento(dto.getFechaNacimiento());

        this.personaRepository.save(persona);
    }
}
