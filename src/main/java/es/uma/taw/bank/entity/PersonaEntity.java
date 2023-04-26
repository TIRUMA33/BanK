package es.uma.taw.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PERSONA", schema = "taw", catalog = "")
public class PersonaEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "APELLIDO1", nullable = false, length = 45)
    private String apellido1;
    @Basic
    @Column(name = "APELLIDO2", nullable = true, length = 45)
    private String apellido2;
    @Basic
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;
    @OneToMany(mappedBy = "personaByIdPersona")
    private List<EmpresaPersonaEntity> empresaPersonasById;
    @OneToMany(mappedBy = "personaByIdPersona")
    private List<EmpresaClienteEntity> empresaClientesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaEntity persona = (PersonaEntity) o;

        if (id != null ? !id.equals(persona.id) : persona.id != null) return false;
        if (nombre != null ? !nombre.equals(persona.nombre) : persona.nombre != null) return false;
        if (apellido1 != null ? !apellido1.equals(persona.apellido1) : persona.apellido1 != null) return false;
        if (apellido2 != null ? !apellido2.equals(persona.apellido2) : persona.apellido2 != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(persona.fechaNacimiento) : persona.fechaNacimiento != null)
            return false;
        if (dni != null ? !dni.equals(persona.dni) : persona.dni != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido1 != null ? apellido1.hashCode() : 0);
        result = 31 * result + (apellido2 != null ? apellido2.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        return result;
    }
}
