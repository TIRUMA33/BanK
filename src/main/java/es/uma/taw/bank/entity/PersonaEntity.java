package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persona", schema = "taw", catalog = "")
public class PersonaEntity {
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "APELLIDO1", nullable = false, length = 45)
    private String apellido1;
    @Basic
    @Column(name = "APELLIDO2", nullable = false, length = 45)
    private String apellido2;
    @Basic
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Timestamp fechaNacimiento;
    @OneToMany(mappedBy = "personaByNumeroIdentificacion")
    private List<ClienteEntity> cliente;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fechaNacimiento, that.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido1, apellido2, fechaNacimiento);
    }

    public List<ClienteEntity> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteEntity> cliente) {
        this.cliente = cliente;
    }
}
