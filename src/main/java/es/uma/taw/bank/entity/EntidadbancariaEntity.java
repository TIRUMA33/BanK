package es.uma.taw.bank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "entidadbancaria", schema = "taw", catalog = "")
public class EntidadbancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Nombre", nullable = false, length = 45)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadbancariaEntity that = (EntidadbancariaEntity) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
