package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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
    private Timestamp fechaNacimiento;
    @Basic
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;
    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteById;
    @OneToMany(mappedBy = "personaByPersonaRelacionada")
    private List<EmpresaEntity> empresasById;
    @OneToMany(mappedBy = "personaByIdPersona")
    private List<EmpresaPersonaEntity> empresaPersonasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido1, apellido2, fechaNacimiento, dni);
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public List<EmpresaEntity> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(List<EmpresaEntity> empresasById) {
        this.empresasById = empresasById;
    }

    public List<EmpresaPersonaEntity> getEmpresaPersonasById() {
        return empresaPersonasById;
    }

    public void setEmpresaPersonasById(List<EmpresaPersonaEntity> empresaPersonasById) {
        this.empresaPersonasById = empresaPersonasById;
    }
}
